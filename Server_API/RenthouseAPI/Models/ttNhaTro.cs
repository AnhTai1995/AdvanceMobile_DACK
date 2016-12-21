using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RenthouseAPI.Models
{
    public class ttNhaTro
    {
        public int IDNhaTro { get; set; }
        public Nullable<float> DienTich { get; set; }
        public string DiaChi { get; set; }
        public Nullable<int> IDNguoiDang { get; set; }
        public string HinhAnh { get; set; }
        public Nullable<int> GiaPhong { get; set; }
        public int MaDuLieu { get; set; }
        public string MoTa { get; set; }
        public string TinhTrang { get; set; }
        public string Loai { get; set; }
        public string DienThoai { get; set; }
        public string HinhAnh1 { get; set; }
        public string HinhAnh2 { get; set; }
        public string HinhAnh3 { get; set; }
        public Nullable<float> KinhDo { get; set; }
        public Nullable<float> ViDo { get; set; }
        public string ChuThich { get; set; }
    }
}