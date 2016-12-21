using RenthouseAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using System.Web.Http.Description;

namespace RentHouseAPI.Controllers
{
    [RoutePrefix("api/v1")]
    public class RentHouseController : ApiController
    {
        private dbd393a18719f8424eb193a6ca016fa966Entities db = new dbd393a18719f8424eb193a6ca016fa966Entities();

        /// <summary>
        /// GET danh sach nha tro
        /// </summary>
        /// <return></return>
        [Route("nha-tro")]
        [HttpGet]
        public IEnumerable<NhaTro> getNhaTro()
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


        /// <summary>
        /// POST nha tro
        /// </summary>
        /// <param>NhaTro</param>
        /// <return></return>
        [ResponseType(typeof(ttNhaTro))]
        [Route("nha-tro")]
        [HttpPost]
        public bool postNhaTro(ttNhaTro nhatro)
        {
            try
            {
                NhaTro nt = new NhaTro();
                nt.IDNguoiDang = nhatro.IDNguoiDang;
                nt.HinhAnh = nhatro.HinhAnh;
                nt.DiaChi = nhatro.DiaChi;
                nt.DienTich = nhatro.DienTich;
                nt.GiaPhong = nhatro.GiaPhong; 

                db.NhaTroes.Add(nt);
                db.SaveChanges();

                var q = (from e in db.NhaTroes
                         where e.IDNguoiDang == nhatro.IDNguoiDang && e.DiaChi == nhatro.DiaChi && e.GiaPhong == nhatro.GiaPhong && e.DienTich == nhatro.DienTich
                         select e
                    ).ToList().First<NhaTro>();

                ChiTietNhaTro ctnhatro = new ChiTietNhaTro();
                ctnhatro.IDNhaTro = q.IDNhaTro;
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
            catch (Exception e)
            {
                return false;
            }
        }

        /// <summary>
        /// GET danh sach nguoi dung
        /// </summary>
        /// <return></return>
        [Route("nguoi-dung")]
        [HttpGet]
        public IEnumerable<NguoiDung> getNguoiDung()
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

        
        /// <summary>
        /// GET danh sach binh luan theo id nha tro
        /// </summary>
        /// <return></return>
        [Route("binh-luan")]
        [HttpGet]
        public IEnumerable<BinhLuan> getBinhLuan(int id)
        {
            List<BinhLuan> temp = new List<BinhLuan>();
            var q = (from a in db.BinhLuans
                     where a.IDNhaTro == id
                     select a).ToList();
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

        /// <summary>
        /// GET chi tiet nha tro theo id nha tro
        /// </summary>
        /// <return></return>
        [Route("nha-tro")]
        [HttpGet]
        public IEnumerable<ChiTietNhaTro> getChiTietNhaTro(int id)
        {
            List<ChiTietNhaTro> temp = new List<ChiTietNhaTro>();

            var q = (from a in db.ChiTietNhaTroes
                     where a.IDNhaTro == id
                     select a).ToList();

            for (int i = 0; i < q.Count(); i++)
            {
                ChiTietNhaTro t = new ChiTietNhaTro();
                temp.Add(t);
                temp[i].MaDuLieu = q[i].MaDuLieu;
                temp[i].IDNhaTro = q[i].IDNhaTro.Value;
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

    }
}