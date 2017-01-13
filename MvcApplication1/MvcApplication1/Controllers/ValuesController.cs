using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Web.Http;
using System.Xml;

namespace MvcApplication1.Controllers
{
    public class Values1Controller: ApiController
    {
        // GET api/values1
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }


        string key_Gia = "<div class=\"summary_item_info summary_item_info_price\">";
        string key_DoiTuongThue = "Đối tượng:</div>\n								<div class=\"summary_item_info\">";
        string key_Ten = "<title>";
        string key_ThongTinThem = "<span class=\"block_headline\">Thông tin mô tả</span>";
        string key_DiaChi = "Địa chỉ:</div>\n								<div class=\"summary_item_info\">";
        string key_Loai = "Loại tin rao:</div>\n								<div class=\"summary_item_info\">";
        string key_DienTich = "<div class=\"summary_item_info summary_item_info_area\">";
        string key_ThoiGian = "Ngày cập nhật:</div>\n								<div class=\"summary_item_info\">";
        string key_NguoiDang = "Người đăng:</div>\n								<div class=\"summary_item_info\">";
        string key_HinhAnh1 = "data-image=\"";
        string key_HinhAnh2 = "data-image=\"";
        string key_HinhAnh3 = "data-image=\"";
        string key_Sdt = "Điện thoại:</div>\n								<div class=\"summary_item_info\">";
        string image_url = @"";    


        string FILE_NAME = @"https://phongtro123.com/tinh-thanh/ho-chi-minh/page/";

        string[] GetUrlFromPage(string page, string key)
        {
            HashSet<string> listUrl = new HashSet<string>();
            string str;
            using (var wc = new WebClient())
            {
                wc.Encoding = System.Text.Encoding.UTF8;
                str = wc.DownloadString(page);
            }

            int index = 0;
            for (int i = 0; i < 40; i++ )
            {
                index = str.IndexOf(key, index) + key.Length;
                if (index < 0) break;
                int last = str.IndexOf("\" alt", index);
                string rt = str.Substring(index, last - index);
                index++;

                //if (rt.IndexOf(@".htm") >= 0 && rt.Length > 44)
                //{
                //    listUrl.Add(rt);
                //}
                listUrl.Add(rt);
            }

            return listUrl.ToArray();
        }

        ThongTinNha UrlToObject(string url)
        {
            ThongTinNha ttn = new ThongTinNha();

            string str;
            using (var wc = new WebClient())
            {
                wc.Encoding = System.Text.Encoding.UTF8;
                str = wc.DownloadString(url);
            }


            int index, end;

            index = str.IndexOf(key_Gia);
            end = str.IndexOf("<", index + key_Gia.Length);
            ttn.GiaThue = index >= 0 ? str.Substring(index + key_Gia.Length, end - index - key_Gia.Length).Trim() : "null";

            index = str.IndexOf(key_DoiTuongThue);
            end = str.IndexOf("<", index + key_DoiTuongThue.Length);
            ttn.DoiTuongThue = index>=0?str.Substring(index + key_DoiTuongThue.Length, end - index - key_DoiTuongThue.Length).Trim():"null";

            index = str.IndexOf(key_Ten);
            end = str.IndexOf("<", index + key_Ten.Length);
            ttn.Ten = index>=0?str.Substring(index + key_Ten.Length, end - index - key_Ten.Length).Trim():"null";

            index = str.IndexOf(key_ThongTinThem);
            end = str.IndexOf("</div>", index + key_ThongTinThem.Length);
            ttn.ThongTinThem = index>=0?str.Substring(index + key_ThongTinThem.Length, end - index - key_ThongTinThem.Length).Trim()
            //    .Replace("<br /><br />", "\n")
                .Replace("<p>", "")
                .Replace("</p>", "\n") : "null";

            index = str.IndexOf(key_DiaChi);
            end = str.IndexOf("<", index + key_DiaChi.Length);
            ttn.DiaChi = index>=0?str.Substring(index + key_DiaChi.Length, end - index - key_DiaChi.Length).Trim():"null";

            index = str.IndexOf(key_Loai);
            end = str.IndexOf("</div>", index + key_Loai.Length);
            ttn.Loai = index>=0?str.Substring(index + key_Loai.Length, end - index - key_Loai.Length).Trim():"null";

            index = str.IndexOf(key_DienTich);
            end = str.IndexOf("<", index + key_DienTich.Length);
            ttn.DienTich = index>=0?str.Substring(index + key_DienTich.Length, end - index - key_DienTich.Length).Trim():"null";

            index = str.IndexOf(key_ThoiGian);
            end = str.IndexOf("<", index + key_ThoiGian.Length);
            ttn.ThoiGian = index >= 0 ? str.Substring(index + key_ThoiGian.Length, end - index - key_ThoiGian.Length).Trim() : "null";

            index = str.IndexOf(key_HinhAnh1);
            end = str.IndexOf("\"", index + key_HinhAnh1.Length);
            ttn.HinhAnh1 = index >= 0 ? image_url+str.Substring(index + key_HinhAnh1.Length, end - index - key_HinhAnh1.Length).Trim() : "null";

            index = str.IndexOf(key_HinhAnh2, end);
            end = str.IndexOf("\"", index + key_HinhAnh2.Length);
            ttn.HinhAnh2 = index >= 0 ? image_url+str.Substring(index + key_HinhAnh2.Length, end - index - key_HinhAnh2.Length).Trim() : "null";

            index = str.IndexOf(key_HinhAnh3, end);
            end = str.IndexOf("\"", index + key_HinhAnh3.Length);
            ttn.HinhAnh3 = index >= 0 ? image_url+str.Substring(index + key_HinhAnh3.Length, end - index - key_HinhAnh3.Length).Trim() : "null";

            index = str.IndexOf(key_NguoiDang);
            end = str.IndexOf("<", index + key_Sdt.Length);
            ttn.NguoiDang = index >= 0 ? str.Substring(index + key_Sdt.Length, end - index - key_Sdt.Length).Trim() : "null";

            index = str.IndexOf(key_Sdt);
            end = str.IndexOf("</div>", index + key_Sdt.Length);
            ttn.Sdt = index >= 0 ? str.Substring(index + key_Sdt.Length, end - index - key_Sdt.Length).Trim() : "null";

            return ttn;
        }


        // GET api/values1
        public ThongTinNha[] Get(int id)
        {
            string key = "<a class=\"post-link\" href=\"";

            List<ThongTinNha> ttn = new List<ThongTinNha>();

            for (int i = 1; i <= id; i++)
            {


                string[] list = GetUrlFromPage(FILE_NAME + i, key);
                foreach (string url in list)
                {
                    ThongTinNha temp = UrlToObject(url);
                    ttn.Add(temp);
                }


            }


            //HtmlWeb hw = new HtmlWeb();

            //HtmlDocument doc = hw.Load("http://htmlagilitypack.codeplex.com/");
            //foreach (HtmlNode link in doc.DocumentNode.SelectNodes("//a[@href]"))
            //{
            //    ThongTinNha ttt = new ThongTinNha();
            //    ttt.HinhAnh1 = link.InnerText;
            //}


            return ttn.ToArray();
        }






















        // POST api/values
        public void Post([FromBody]string value)
        {
        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
        }
    }
}