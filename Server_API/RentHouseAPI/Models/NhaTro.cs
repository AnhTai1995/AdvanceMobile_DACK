//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace RenthouseAPI.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class NhaTro
    {
        public NhaTro()
        {
            this.BinhLuans = new HashSet<BinhLuan>();
            this.ChiTietNhaTroes = new HashSet<ChiTietNhaTro>();
        }
    
        public int ID { get; set; }
        public string IDNhaTro { get; set; }
        public string DienTich { get; set; }
        public string DiaChi { get; set; }
        public string IDNguoiDang { get; set; }
        public string HinhAnh { get; set; }
        public string GiaPhong { get; set; }
        public string NgayDang { get; set; }
        public Nullable<bool> AutoExtract { get; set; }
    
        public virtual ICollection<BinhLuan> BinhLuans { get; set; }
        public virtual ICollection<ChiTietNhaTro> ChiTietNhaTroes { get; set; }
        public virtual NguoiDung NguoiDung { get; set; }
    }
}
