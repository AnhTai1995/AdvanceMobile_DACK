using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RenthouseAPI.Models
{
    public class key_NhaTro
    {
        public string key_Gia { get; set; }
        public string key_DoiTuongThue { get; set; }
        public string key_Ten { get; set; }
        public string key_ThongTinThem { get; set; }
        public string key_DiaChi { get; set; }
        public string key_Loai { get; set; }
        public string key_DienTich { get; set; }
        public string key_ThoiGian { get; set; }
        public string key_NguoiDang { get; set; }
        public string key_HinhAnh1 { get; set; }
        public string key_HinhAnh2 { get; set; }
        public string key_HinhAnh3 { get; set; }
        public string key_Sdt { get; set; }
        public string image_url { get; set; }
        public string FILE_NAME { get; set; }

        public key_NhaTro()
        {
            key_Gia = "<div class=\"summary_item_info summary_item_info_price\">";
            key_DoiTuongThue = "Đối tượng:</div>\n								<div class=\"summary_item_info\">";
            key_Ten = "<title>";
            key_ThongTinThem = "<span class=\"block_headline\">Thông tin mô tả</span>";
            key_DiaChi = "Địa chỉ:</div>\n								<div class=\"summary_item_info\">";
            key_Loai = "Loại tin rao:</div>\n								<div class=\"summary_item_info\">";
            key_DienTich = "<div class=\"summary_item_info summary_item_info_area\">";
            key_ThoiGian = "Ngày cập nhật:</div>\n								<div class=\"summary_item_info\">";
            key_NguoiDang = "Người đăng:</div>\n								<div class=\"summary_item_info\">";
            key_HinhAnh1 = "data-image=\"";
            key_HinhAnh2 = "data-image=\"";
            key_HinhAnh3 = "data-image=\"";
            key_Sdt = "Điện thoại:</div>\n								<div class=\"summary_item_info\">";
            image_url = @"";

            FILE_NAME = @"https://phongtro123.com/tinh-thanh/ho-chi-minh/page/";
        }
    }
}