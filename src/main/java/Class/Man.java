/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import com.model.DanhMuc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vital
 */
public class Man {
    public static void main(String[] args) {
       List list=new ArrayList();
//        DanhMuc temp=(DanhMuc) danhMuc.getClass().cast(danhMuc);
        list.add(new DanhMuc(1,"danh-sach"));
        list.add(new DanhMuc(2,"ky-nang"));
        list.add(new DanhMuc(3,"so-luong"));
        HashMap<String,Integer> map=new HashMap<String, Integer>();
        map.put("danh-sach",10);
        map.put("ky-nang",10);
        map.put("so-Luong",10);
        List res=new ArrayList();
        res.add(list);
        res.add(map);
        
        List xuLy=(List) res.get(0);
        HashMap<String,Integer>  mapURL=(HashMap) res.get(1);
//        for(Object e:xuLy)
//        {
//           DanhMuc danhMuc=(DanhMuc) e;
//           System.out.println(danhMuc.getIdDanhMuc()+ " soLuong : " +""+show(mapURL,danhMuc.getUrl()));
//        }
        List count=new ArrayList();
        count.add(new Long(12));
        Object obj[]=count.toArray();
        Long result=(Long) obj[0];
        System.out.println(result);
    }
    public static int  show(HashMap<String, Integer> hashMap,String url) {
        Set<String> keySet = hashMap.keySet();
        for (String key : keySet) {
            if(key.equals(url)){
                return hashMap.get(key);
            }   
        }
        return 0;
    }

}
