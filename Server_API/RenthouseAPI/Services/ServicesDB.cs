using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using RenthouseAPI.Models;
using System.Threading.Tasks;
using System.Net;

namespace RenthouseAPI.Services
{
    public class ServicesDB
    {
        private NHATROEntities db = new NHATROEntities();
        private key_NhaTro key_NhaTro = new key_NhaTro();

        //Kiem tra tai khoan 
        public bool checkAcc(string _userName, string _passWord)
        {
            var q = (from e in db.NguoiDungs
                     where e.Username == _userName
                     select new
                     {
                         name = e.Username,
                         pass = e.Pass
                     }).ToList();
            if (q.Count == 0)
                return false;
            else
                if (q[0].pass != _passWord)
                return false;
            return true;
        }


        // Lay danh sach nha tro
        public async Task<IEnumerable<NhaTro>> listNhaTro()
        {
            List<NhaTro> temp = new List<NhaTro>();
            for (int i = 0; i < db.NhaTroes.Count(); i++)
            {
                NhaTro t = new NhaTro();
                temp.Add(t);
                temp[i].IDNhaTro = db.NhaTroes.ToList()[i].IDNhaTro;
                temp[i].IDNguoiDang = db.NhaTroes.ToList()[i].IDNguoiDang;
                temp[i].DienTich = db.NhaTroes.ToList()[i].DienTich;
                temp[i].DiaChi = db.NhaTroes.ToList()[i].DiaChi;
                temp[i].GiaPhong = db.NhaTroes.ToList()[i].GiaPhong;
                temp[i].HinhAnh = db.NhaTroes.ToList()[i].HinhAnh;
            }
            return temp;
        }

        // Lay thong tin chi tiet nha tro
        public async Task<IEnumerable<ChiTietNhaTro>> inforNhaTro(string id)
        {
            List<ChiTietNhaTro> temp = new List<ChiTietNhaTro>();

            var q = (from a in db.ChiTietNhaTroes
                     where a.IDNhaTro == id
                     select a).ToList();

            for (int i = 0; i < q.Count(); i++)
            {
                ChiTietNhaTro t = new ChiTietNhaTro();
                temp.Add(t);
                temp[i].IDNhaTro = q[i].IDNhaTro;
                temp[i].MoTa = q[i].MoTa;
                temp[i].DienThoai = q[i].DienThoai;
                temp[i].TinhTrang = q[i].TinhTrang;
                temp[i].Loai = q[i].Loai;
                temp[i].HinhAnh1 = q[i].HinhAnh1;
                temp[i].HinhAnh2 = q[i].HinhAnh2;
                temp[i].HinhAnh3 = q[i].HinhAnh3;
                temp[i].KinhDo = q[i].KinhDo.Value;
                temp[i].ViDo = q[i].ViDo.Value;
                temp[i].ChuThich = q[i].ChuThich;
            }

            return temp;
        }

        // Them nha tro
        public async Task<bool> addNhaTro(ttNhaTro nhatro)
        {
            NhaTro nt = new NhaTro();
            string IDNhaTro;
            nt.IDNguoiDang = nhatro.IDNguoiDang;
            nt.HinhAnh = nhatro.HinhAnh;
            nt.DiaChi = nhatro.DiaChi;
            nt.DienTich = nhatro.DienTich;
            nt.GiaPhong = nhatro.GiaPhong;

            db.NhaTroes.Add(nt);
            db.SaveChanges();

            db.Entry(nt).GetDatabaseValues();
            IDNhaTro = nt.IDNhaTro;

            ChiTietNhaTro ctnhatro = new ChiTietNhaTro();
            ctnhatro.IDNhaTro = IDNhaTro;
            ctnhatro.MoTa = nhatro.MoTa;
            ctnhatro.TinhTrang = nhatro.TinhTrang;
            ctnhatro.Loai = nhatro.Loai;
            ctnhatro.DienThoai = nhatro.DienThoai;
            ctnhatro.HinhAnh1 = nhatro.HinhAnh1;
            ctnhatro.HinhAnh2 = nhatro.HinhAnh2;
            ctnhatro.HinhAnh3 = nhatro.HinhAnh3;
            ctnhatro.KinhDo = nhatro.KinhDo;
            ctnhatro.ViDo = nhatro.ViDo;
            ctnhatro.ChuThich = nhatro.ChuThich;

            db.ChiTietNhaTroes.Add(ctnhatro);
            db.SaveChanges();

            return true;
        }

        // Lay danh sach binh luan theo id nha tro
        public async Task<IEnumerable<BinhLuan>> listBinhLuan(string id)
        {
            List<BinhLuan> temp = new List<BinhLuan>();
            var q = (from e in db.BinhLuans
                     where e.IDNhaTro == id
                     select e
                     ).ToList();
            for (int i = 0; i < q.Count(); i++)
            {
                BinhLuan t = new BinhLuan();
                temp.Add(t);
                temp[i].IDBinhLuan = q[i].IDBinhLuan;
                temp[i].IDNguoiDung = q[i].IDNguoiDung;
                temp[i].IDNhaTro = q[i].IDNhaTro;
                temp[i].NoiDung = q[i].NoiDung;
                temp[i].ThoiGianBL = q[i].ThoiGianBL;
            }

            return temp;
        }

        // Lay danh sach nguoi dung
        public async Task<IEnumerable<NguoiDung>> listNguoiDung()
        {
            List<NguoiDung> temp = new List<NguoiDung>();
            for (int i = 0; i < db.NguoiDungs.Count(); i++)
            {
                NguoiDung t = new NguoiDung();
                temp.Add(t);
                temp[i].IDNguoiDung = db.NguoiDungs.ToList()[i].IDNguoiDung;
                temp[i].Ten = db.NguoiDungs.ToList()[i].Ten;
                temp[i].Username = db.NguoiDungs.ToList()[i].Username;
                temp[i].Pass = db.NguoiDungs.ToList()[i].Pass;
                temp[i].Mail = db.NguoiDungs.ToList()[i].Mail;
                temp[i].NamSinh = db.NguoiDungs.ToList()[i].NamSinh.Value;
                temp[i].SoDienThoai = db.NguoiDungs.ToList()[i].SoDienThoai;
            }

            return temp;
        }

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
            for (int i = 0; i < 40; i++)
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

        ttNhaTro UrlToObject(string url)
        {
            ttNhaTro ttn = new ttNhaTro();

            string str;
            using (var wc = new WebClient())
            {
                wc.Encoding = System.Text.Encoding.UTF8;
                str = wc.DownloadString(url);
            }


            int index, end;

            index = str.IndexOf(key_NhaTro.key_Gia);
            end = str.IndexOf("<", index + key_NhaTro.key_Gia.Length);
            ttn.GiaPhong = index >= 0 ? str.Substring(index + key_NhaTro.key_Gia.Length, end - index - key_NhaTro.key_Gia.Length).Trim() : "null";

            //index = str.IndexOf(key_DoiTuongThue);
            //end = str.IndexOf("<", index + key_DoiTuongThue.Length);
            //ttn.DoiTuongThue = index >= 0 ? str.Substring(index + key_DoiTuongThue.Length, end - index - key_DoiTuongThue.Length).Trim() : "null";

            //index = str.IndexOf(key_Ten);
            //end = str.IndexOf("<", index + key_Ten.Length);
            //ttn.Ten = index >= 0 ? str.Substring(index + key_Ten.Length, end - index - key_Ten.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_ThongTinThem);
            end = str.IndexOf("</div>", index + key_NhaTro.key_ThongTinThem.Length);

            string _mota = index >= 0 ? str.Substring(index + key_NhaTro.key_ThongTinThem.Length, end - index - key_NhaTro.key_ThongTinThem.Length).Trim()
            //    .Replace("<br /><br />", "\n")
                .Replace("<p>", "")
                .Replace("</p>", "\n") : "null";
            if (_mota.Length >= 3000) { _mota = _mota.Substring(0, 2900); }
            ttn.MoTa = _mota;

            index = str.IndexOf(key_NhaTro.key_DiaChi);
            end = str.IndexOf("<", index + key_NhaTro.key_DiaChi.Length);
            ttn.DiaChi = index >= 0 ? str.Substring(index + key_NhaTro.key_DiaChi.Length, end - index - key_NhaTro.key_DiaChi.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_Loai);
            end = str.IndexOf("</div>", index + key_NhaTro.key_Loai.Length);
            ttn.Loai = index >= 0 ? str.Substring(index + key_NhaTro.key_Loai.Length, end - index - key_NhaTro.key_Loai.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_DienTich);
            end = str.IndexOf("<", index + key_NhaTro.key_DienTich.Length);
            ttn.DienTich = index >= 0 ? str.Substring(index + key_NhaTro.key_DienTich.Length, end - index - key_NhaTro.key_DienTich.Length).Trim() : "null";

            //index = str.IndexOf(key_NhaTro.key_ThoiGian);
            //end = str.IndexOf("<", index + key_NhaTro.key_ThoiGian.Length);
            //ttn.NgayDang = index >= 0 ? str.Substring(index + key_NhaTro.key_ThoiGian.Length, end - index - key_NhaTro.key_ThoiGian.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_HinhAnh1);
            end = str.IndexOf("\"", index + key_NhaTro.key_HinhAnh1.Length);
            ttn.HinhAnh1 = index >= 0 ? key_NhaTro.image_url + str.Substring(index + key_NhaTro.key_HinhAnh1.Length, end - index - key_NhaTro.key_HinhAnh1.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_HinhAnh2, end);
            end = str.IndexOf("\"", index + key_NhaTro.key_HinhAnh2.Length);
            ttn.HinhAnh2 = index >= 0 ? key_NhaTro.image_url + str.Substring(index + key_NhaTro.key_HinhAnh2.Length, end - index - key_NhaTro.key_HinhAnh2.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_HinhAnh3, end);
            end = str.IndexOf("\"", index + key_NhaTro.key_HinhAnh3.Length);
            ttn.HinhAnh3 = index >= 0 ? key_NhaTro.image_url + str.Substring(index + key_NhaTro.key_HinhAnh3.Length, end - index - key_NhaTro.key_HinhAnh3.Length).Trim() : "null";

            //index = str.IndexOf(key_NguoiDang);
            //end = str.IndexOf("<", index + key_Sdt.Length);
            //ttn.NguoiDang = index >= 0 ? str.Substring(index + key_Sdt.Length, end - index - key_Sdt.Length).Trim() : "null";

            index = str.IndexOf(key_NhaTro.key_Sdt);
            end = str.IndexOf("</div>", index + key_NhaTro.key_Sdt.Length);
            ttn.DienThoai = index >= 0 ? str.Substring(index + key_NhaTro.key_Sdt.Length, end - index - key_NhaTro.key_Sdt.Length).Trim() : "null";

            return ttn;
        }

        //Lay danh sach nha tro tu phong tro 123
        public async Task<List<ttNhaTro>> listNhaTro123(int soluong)
        {
            string key = "<a class=\"post-link\" href=\"";
            string IDNhaTro;
            List<ttNhaTro> ttn = new List<ttNhaTro>();

            //db.Database.ExecuteSqlCommand("DBCC CHECKIDENT('ChiTietNhaTro', RESEED, 2)");
            //db.Database.ExecuteSqlCommand("DBCC CHECKIDENT('NhaTro', RESEED, 2)");

            var rows = (from o in db.ChiTietNhaTroes
                        where o.AutoExtract == true
                        select o).ToList();

            for (int i = 1; i <= soluong; i++)
            {

                string[] list = GetUrlFromPage(key_NhaTro.FILE_NAME + i, key);
                foreach (string url in list)
                {
                    bool add = true;
                    ttNhaTro temp = UrlToObject(url);

                    //Kiem tra su trung lap
                    if (rows.Count != 0)
                    {
                        for (int j = rows.Count - 1; j >= rows.Count - 20; j--)
                        {
                            if (temp.MoTa == rows[j].MoTa)
                            {
                                add = false;
                            }
                        }
                    }

                    if (add)
                    {
                        string today = DateTime.Today.Date.ToString("dd'/'MM'/'yyyy");

                        NhaTro nt = new NhaTro();
                        nt.IDNguoiDang = "ND00001";
                        nt.HinhAnh = temp.HinhAnh1;
                        nt.DiaChi = temp.DiaChi;
                        nt.DienTich = temp.DienTich;
                        nt.GiaPhong = temp.GiaPhong;
                        nt.NgayDang = today;
                        nt.AutoExtract = true;

                        db.NhaTroes.Add(nt);
                        db.SaveChanges();

                        db.Entry(nt).GetDatabaseValues();
                        IDNhaTro = nt.IDNhaTro;

                        ChiTietNhaTro ctnhatro = new ChiTietNhaTro();
                        ctnhatro.IDNhaTro = IDNhaTro;
                        ctnhatro.MoTa = temp.MoTa;
                        ctnhatro.TinhTrang = temp.TinhTrang;
                        ctnhatro.Loai = temp.Loai;
                        ctnhatro.DienThoai = temp.DienThoai;
                        ctnhatro.HinhAnh1 = temp.HinhAnh1;
                        ctnhatro.HinhAnh2 = temp.HinhAnh2;
                        ctnhatro.HinhAnh3 = temp.HinhAnh3;
                        ctnhatro.KinhDo = temp.KinhDo;
                        ctnhatro.ViDo = temp.ViDo;
                        ctnhatro.ChuThich = temp.ChuThich;
                        ctnhatro.AutoExtract = true;

                        db.ChiTietNhaTroes.Add(ctnhatro);
                        db.SaveChanges();

                        ttn.Add(temp);
                    }

                }
            }
            return ttn;
        }
    }
}