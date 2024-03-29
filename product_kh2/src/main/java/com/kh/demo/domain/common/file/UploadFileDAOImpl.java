package com.kh.demo.domain.common.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UploadFileDAOImpl implements UploadFileDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 업로드 파일 등록 - 단건
     *
     * @param uploadFile
     * @return 파일Id
     */
    @Override
    public Long addFile(UploadFile uploadFile) {

        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO uploadfile ( ");
        sql.append("  uploadfile_id, ");
        sql.append("  code, ");
        sql.append("  rid, ");
        sql.append("  store_filename, ");
        sql.append("  upload_filename, ");
        sql.append("  fsize, ");
        sql.append("  ftype ");
        sql.append(") VALUES ( ");
        sql.append("  uploadfile_uploadfile_id_seq.nextval, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ? ");
        sql.append(") ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"uploadfile_id"});
                pstmt.setString(1, uploadFile.getCode());
                pstmt.setLong(2, uploadFile.getRid());
                pstmt.setString(3, uploadFile.getStoreFilename());
                pstmt.setString(4, uploadFile.getUploadFilename());
                pstmt.setString(5, uploadFile.getFsize());
                pstmt.setString(6, uploadFile.getFtype());
                return pstmt;
            }
        },keyHolder);

        return Long.valueOf(keyHolder.getKeys().get("uploadfile_id").toString());
    }


    /**
     * 업로드 파일 등록 - 여러건
     *
     * @param uploadFile
     */
    @Override
    public void addFile(List<UploadFile> uploadFile) {

        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO uploadfile ( ");
        sql.append("  uploadfile_id, ");
        sql.append("  code, ");
        sql.append("  rid, ");
        sql.append("  store_filename, ");
        sql.append("  upload_filename, ");
        sql.append("  fsize, ");
        sql.append("  ftype ");
        sql.append(") VALUES ( ");
        sql.append("  uploadfile_uploadfile_id_seq.nextval, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ?, ");
        sql.append("  ? ");
        sql.append(") ");

        //배치 처리 : 여러건의 갱신작업을 한꺼번에 처리하므로 단건처리할때보다 성능이 좋다.
        jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, uploadFile.get(i).getCode());
                ps.setLong(2, uploadFile.get(i).getRid());
                ps.setString(3, uploadFile.get(i).getStoreFilename());
                ps.setString(4, uploadFile.get(i).getUploadFilename());
                ps.setString(5, uploadFile.get(i).getFsize());
                ps.setString(6, uploadFile.get(i).getFtype());
            }

            //배치처리할 건수
            @Override
            public int getBatchSize() {
                return uploadFile.size();
            }
        });

    }


    /**
     * 업로드 파일 조회 - 여러건
     *
     * @param code
     * @param rid
     * @return
     */
    @Override
    public List<UploadFile> getFilesByCodeWithRid(String code, Long rid) {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT  ");
        sql.append("   uploadfile_id, ");
        sql.append("   code, ");
        sql.append("   rid,  ");
        sql.append("   store_filename, ");
        sql.append("   upload_filename,  ");
        sql.append("   fsize,  ");
        sql.append("   ftype,  ");
        sql.append("   cdate,  ");
        sql.append("   udate ");
        sql.append("  FROM  uploadfile  ");
        sql.append(" WHERE CODE = ?  ");
        sql.append("   AND RID = ?  ");

        List<UploadFile> list = jdbcTemplate.query(sql.toString(),
                // DB table의 컬럼명(snake_case) => java 객체(camelCase)로 자동 변환
                new BeanPropertyRowMapper<>(UploadFile.class), code, rid);
        log.info("list={}",list);
        return list;
    }


    /**
     * 업로드 파일 조회 - 단건
     *
     * @param uploadfileId
     * @return
     */
    @Override
    public Optional<UploadFile> findFileByUploadFileId(Long uploadfileId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * ");
        sql.append("  from uploadfile ");
        sql.append(" where uploadfile_id = ? ");

        try {
            UploadFile uploadFile = jdbcTemplate.queryForObject(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(UploadFile.class),
                    uploadfileId
            );
            return Optional.of(uploadFile);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }


    /**
     * 첨부파일 삭제 by uplaodfileId
     *
     * @param uploadfileId 첨부파일아이디
     * @return 삭제한 레코드수
     */
    @Override
    public int deleteFileByUploadFildId(Long uploadfileId) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from uploadfile ");
        sql.append(" where uploadfile_id = ? ");

        return jdbcTemplate.update(sql.toString(), uploadfileId);
    }


    /**
     * 첨부파일 삭제 By code, rid
     *
     * @param code 첨부파일 분류코드
     * @param rid  첨부파일아이디
     * @return 삭제한 레코드수
     */
    @Override
    public int deleteFileByCodeWithRid(String code, Long rid) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from uploadfile ");
        sql.append(" where code = ? ");
        sql.append("   and rid = ? ");

        return jdbcTemplate.update(sql.toString(), code, rid);
    }

}
