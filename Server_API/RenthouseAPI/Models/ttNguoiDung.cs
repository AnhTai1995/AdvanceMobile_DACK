using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RenthouseAPI.Models
{
    public class ttNguoiDung
    {
        public string IDNguoiDung { get; set; }
        public string Username { get; set; }
        public string Ten { get; set; }
        public string Pass { get; set; }
        public string NamSinh { get; set; }
        public string GioiTinh { get; set; }
        public string Mail { get; set; }
        public string Avatar { get; set; }
        public string SDT { get; set; }
        public bool isFacebook { get; set; }
    }
}