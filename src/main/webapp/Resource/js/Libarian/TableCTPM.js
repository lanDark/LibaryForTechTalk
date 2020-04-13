export default  class TableCTPM{
    buildTable(ChiTietPhieuMuonList){
              
        $('#tableModalRequestHold tbody').empty();
        ChiTietPhieuMuonList.forEach((item)=>{
            let sach = item.sach;
            $('#tableModalRequestHold tbody').append(
                `
                    <tr>
                        <td><div><img style="height:100px ;width:100px"src="http://localhost:8084/Spring/Resource/images/books/${sach.hinhAnhs[0].src}" /> </div> </td>
                        <td>${sach.tenSach}</td>   
                        <td>${item.soLuong}</td>
                    </tr>
                `    
                );
        });
              
    }
}