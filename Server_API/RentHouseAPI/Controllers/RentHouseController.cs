using RentHouseAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Web.Http.Description;

namespace RentHouseAPI.Controllers
{
    [RoutePrefix("api/v1")]
    public class RentHouseController : ApiController
    {
        private NHATROEntities db = new NHATROEntities();

        /// <summary>
        /// GET danh sach nha tro
        /// </summary>
        /// <return></return>
        [Route("nha-tro")]
        [HttpGet]
        public IEnumerable<NhaTro> getNhaTro()
        {
            List<NhaTro> temp = new List<NhaTro>();
            for (int i=0; i<db.NhaTroes.Count(); i++)
            {
                NhaTro t = new NhaTro();
                temp.Add(t);
                temp[i].IDNhaTro = db.NhaTroes.ToList()[i].IDNhaTro;
                temp[i].IDNguoiDang = db.NhaTroes.ToList()[i].IDNguoiDang;
                temp[i].DienTich = db.NhaTroes.ToList()[i].DienTich;
                temp[i].SoNha = db.NhaTroes.ToList()[i].SoNha;
                temp[i].GiaPhong = db.NhaTroes.ToList()[i].GiaPhong;
                temp[i].MaDuong = db.NhaTroes.ToList()[i].MaDuong;
                temp[i].MaPhuong = db.NhaTroes.ToList()[i].MaPhuong;
                temp[i].MaQuanHuyen = db.NhaTroes.ToList()[i].MaQuanHuyen;
                temp[i].MaTinhThanh = db.NhaTroes.ToList()[i].MaTinhThanh;
                temp[i].TinhTrang = db.NhaTroes.ToList()[i].TinhTrang;
                temp[i].HinhAnh = db.NhaTroes.ToList()[i].HinhAnh;

            }

            return temp;
        }


        /// <summary>
        /// POST nha tro
        /// </summary>
        /// <param>NhaTro</param>
        /// <return></return>
        [ResponseType(typeof(NhaTro))]
        [Route("nha-tro")]
        [HttpPost]
        public bool postNhaTro(NhaTro nhatro)
        {
            try
            {
                db.NhaTroes.Add(nhatro);
                db.SaveChanges();
                return true;
            }
            catch(Exception e)
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
        /// POST nguoi dung
        /// </summary>
        /// <param>NhaTro</param>
        /// <return></return>
        [ResponseType(typeof(NguoiDung))]
        [Route("nha-tro")]
        [HttpPost]
        public bool postNguoiDung(NguoiDung nguoidung)
        {
            try
            {
                db.NguoiDungs.Add(nguoidung);
                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
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
                temp[i].DienThoai = q[i].DienThoai.Value;
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