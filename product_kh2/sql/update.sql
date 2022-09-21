---------
--÷������
---------
create table uploadfile(
    uploadfile_id   number(10),     --���Ͼ��̵�
    code            varchar2(11),   --�з��ڵ�
    rid             varchar2(10),     --������ȣ(�Խñ۹�ȣ��)
    store_filename  varchar2(100),   --�����������ϸ�
    upload_filename varchar2(100),   --���ε����ϸ�(������ ���ε������ϸ�)
    fsize           varchar2(45),   --���ε�����ũ��(����byte)
    ftype           varchar2(100),   --��������(mimetype)
    cdate           timestamp default systimestamp, --����Ͻ�
    udate           timestamp default systimestamp  --�����Ͻ�
);
--�⺻Ű
alter table uploadfile add constraint uploadfile_uploadfile_id_pk primary key(uploadfile_id);

--�ܷ�Ű
alter table uploadfile add constraint uploadfile_uploadfile_id_fk
    foreign key(code) references code(code_id);

--��������
alter table uploadfile modify code constraint uploadfile_code_nn not null;
alter table uploadfile modify rid constraint uploadfile_rid_nn not null;
alter table uploadfile modify store_filename constraint uploadfile_store_filename_nn not null;
alter table uploadfile modify upload_filename constraint uploadfile_upload_filename_nn not null;
alter table uploadfile modify fsize constraint uploadfile_fsize_nn not null;
alter table uploadfile modify ftype constraint uploadfile_ftype_nn not null;

--������
create sequence uploadfile_uploadfile_id_seq;