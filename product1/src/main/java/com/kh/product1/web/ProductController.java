package com.kh.product1.web;

import com.kh.product1.domain.Product;
import com.kh.product1.domain.svc.ProductSVC;
import com.kh.product1.web.form.AddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductSVC productSVC;


    //상품등록화면
    @GetMapping("/add")
    public String addForm() {

        return "addForm";
    }

    //상품등록처리
    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute  AddForm addForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        Product product = new Product();
        product.setPname(addForm.getPname());
        product.setQuantity(addForm.getQuantity());
        product.setPrice(addForm.getPrice());
        Product addedProduct = productSVC.add(product);

        Long pid = addedProduct.getProductId();
        redirectAttributes.addAttribute("pid", pid);
        return "redirect:/product/{pid}";
    }

    //등록 처리
    @PostMapping("/add")
    public String save(SaveForm saveForm) {
        log.info("saveForm: {}", saveForm);

        Product product = new Product();
        product.setPname(saveForm.getPname());
        product.setQuantity(saveForm.getQuantity());
        product.setPrice(saveForm.getPrice());

        Product savedProduct = productSVC.save(product);

        return "redirect:/products/"+savedProduct.getProductId();    //상품상세 요청 url
    }

    //상품전체목록화면
    @GetMapping
    public String list(Model model) {

        List<Product> list = productSVC.products();
        model.addAttribute("list", list);
        return "productsForm";
    }

}
