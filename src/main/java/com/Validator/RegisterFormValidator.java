    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Validator;


import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author vital
 */
@Component
public class RegisterFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }


    
    @Override
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","","email Không được để trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"matKhau","","Không được để trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"matKhau2","","Không được để trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"diaChi","","Không được để trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"sdt","","Không được để trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"hoTen","","Không được để trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cmnd","","Không được để trống");
        if(errors.getErrorCount() == 0)
            regex(target, errors);
    }
    
    void regex(Object target,Errors erros){
        RegisterForm form = (RegisterForm) target;
        String emailRegex = "[a-zA-Z0-9]*(@[a-z]*)+([.][a-z]{2,4}){1,2}";
        String sdtRegex = "[0-9]{10}";
        String diaChiRegex = "[\\p{L}0-9,. ]{5,100}";
        String cmndRegex = "[0-9]{9}";
        String matKhauRegex = "[a-zA-z0-9]{6,16}";
        String hoTenRegex = "[\\p{L} ]{5,100}";
        
        if(!form.getEmail().matches(emailRegex))
            erros.rejectValue("email","","Email không hợp lệ");

        if(!(form.getSdt().matches(sdtRegex)) )
            erros.rejectValue("sdt","","Số điện thoại không hợp lệ");
        
        if(!(form.getDiaChi().matches(diaChiRegex)) )
            erros.rejectValue("diaChi","","Địa chỉ không hợp lệ");
        
        if(!(form.getCmnd().matches(cmndRegex)) )
            erros.rejectValue("cmnd","","cmnd không hợp lệ");
        
        if(!(form.getHoTen().matches(hoTenRegex)) )
            erros.rejectValue("hoTen","","Họ tên không hợp lệ");
        
        if(!(form.getMatKhau().equals(form.getMatKhau2() ) ))
            erros.rejectValue("matKhau","","mật khẩu không giống nhau");
        else{
            if(!(form.getMatKhau().matches(matKhauRegex))){
                 erros.rejectValue("matKhau","","Mật khẩu không hợp lệ");
            }
            if(!(form.getMatKhau2().matches(matKhauRegex))){
                 erros.rejectValue("matKhau2","","Mật khẩu không hợp lệ");
            }    
        }
        


        

    }

}
