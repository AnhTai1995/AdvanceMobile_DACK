using RenthouseAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using System.Web.Http.Description;
using RenthouseAPI.Services;
using System.Net.Http;
using System.Net;
using System.Threading.Tasks;
using System.Collections;

namespace RentHouseAPI.Controllers
{
    [RoutePrefix("api/v1")]
    public class RentHouseController : ApiController
    {
        private NHATROEntities db = new NHATROEntities();
        private ServicesDB Service = new ServicesDB();

        //---------------NHA TRO ----------------------//
        #region GET

        /// <summary>
        /// GET danh sach nha tro
        /// </summary>
        /// <return></return>
        [Route("nha-tro")]
        [HttpGet]
        public async Task<IEnumerable<ttNhaTro>> getNhaTro()
        {
            try
            {
                return await Service.listNhaTro();
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        /// <summary>
        /// GET chi tiet nha tro theo id nha tro
        /// </summary>
        /// <return></return>
        [Route("nha-tro")]
        [HttpGet]
        public async Task<IEnumerable<ChiTietNhaTro>> getChiTietNhaTro(string id)
        {
            try
            {
                return await Service.inforNhaTro(id);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        //[Authorize(Roles = "Manager")]
        //[Route("nha-tro-2")]
        //[HttpGet]
        //public async Task<IEnumerable<ChiTietNhaTro>> getChiTietNhaTro2(string id)
        //{
        //    try
        //    {
        //        return await Service.inforNhaTro(id);
        //    }
        //    catch (Exception e)
        //    {
        //        throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
        //    }
        //}

        /// <summary>
        /// GET danh sach nha tro theo id nguoi dung
        /// </summary>
        /// <return></return>
        [Route("nha-tro")]
        [HttpGet]
        public async Task<IEnumerable<ttNhaTro>> getDSNhaTroTheoND(string idnd)
        {
            try
            {
                return await Service.listNhaTrobyND(idnd);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        /// <summary>
        /// GET danh sach nha tro da luu theo id nguoi dung
        /// </summary>
        /// <return></return>
        [Route("nha-tro-da-luu")]
        [HttpGet]
        public async Task<IEnumerable<ttNhaTro>> getNhaTroDaLuu(string username)
        {
            try
            {
                return await Service.listNhaTroDaLuu(username);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        /// <summary>
        /// GET tin tuc tu trang chotot.vn
        /// </summary>
        /// <param name="id"></param>
        /// <return></return>
        [Route("nha-tro-123")]
        [HttpGet]
        public async Task<List<ttNhaTro>> getNhaTro123(int soluong)
        {
            try
            {


                return await Service.listNhaTro123(soluong);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        #region POST

        /// <summary>
        /// POST nha tro
        /// </summary>
        /// <data>NhaTro</data>
        /// <return></return>
        [ResponseType(typeof(ttNhaTro))]
        [Route("nha-tro")]
        [HttpPost]
        public async Task<bool> postNhaTro(ttNhaTro nhatro)
        {
            try
            {
                return await Service.addNhaTro(nhatro);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        /// <summary>
        /// POST nha tro da luu
        /// </summary>
        /// <data>NTDL</data>
        /// <return></return>
        [ResponseType(typeof(ttNhaTroDaLuu))]
        [Route("nha-tro-da-luu")]
        [HttpPost]
        public async Task<bool> postNhaTroDaLuu(ttNhaTroDaLuu ntdl)
        {
            try
            {
                return await Service.addNhaTroDaLuu(ntdl);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        #region PUT

        /// <summary>
        /// PUT nha tro
        /// </summary>
        /// <data>NhaTro</data>
        /// <return></return>
        [ResponseType(typeof(ttNhaTro))]
        [Route("nha-tro")]
        [HttpPut]
        public async Task<bool> putNhaTro(ttNhaTro nhatro)
        {
            try
            {
                return await Service.updateNhaTro(nhatro);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        #region DELETE

        /// <summary>
        /// DELETE nha tro da luu
        /// </summary>
        /// <data>NTDL</data>
        /// <return></return>
        [ResponseType(typeof(ttNhaTroDaLuu))]
        [Route("nha-tro-da-luu")]
        [HttpDelete]
        public async Task<bool> deleteNhaTroDaLuu(ttNhaTroDaLuu ntdl)
        {
            try
            {
                return await Service.delNhaTroDaLuu(ntdl);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        /// <summary>
        /// DELETE nha tro
        /// </summary>
        /// <param>id</param>
        /// <return></return>
        [ResponseType(typeof(string))]
        [Route("nha-tro")]
        [HttpDelete]
        public async Task<bool> deleteNhaTro(string id)
        {
            try
            {
                return await Service.delNhaTro(id);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        //---------------------------------------------//




        //---------------NGUOI DUNG ------------------//

        #region GET

        /// <summary>
        /// GET danh sach nguoi dung
        /// </summary>
        /// <return></return>
        [Route("nguoi-dung")]
        [HttpGet]
        public async Task<IEnumerable<ttNguoiDung>> getDSNguoiDung()
        {
            try
            {
                return await Service.listNguoiDung();
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        /// <summary>
        /// GET thong tin nguoi dung theo username
        /// </summary>
        /// <return></return>
        [Route("nguoi-dung")]
        [HttpGet]
        public async Task<ttNguoiDung> getCTNguoiDung(string username)
        {
            try
            {
                return Service.detailNguoiDung(username);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        #region POST

        /// <summary>
        /// POST nguoi dung
        /// </summary>
        /// <data>nguoidung</data>
        /// <return></return>
        [ResponseType(typeof(ttNguoiDung))]
        [Route("nguoi-dung")]
        [HttpPost]
        public async Task<bool> postNguoiDung(ttNguoiDung nguoidung)
        {
            var q = (from e in db.NguoiDungs
                     where e.Username == nguoidung.Username
                     select e).FirstOrDefault();

            if (q != null)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }

            try
            {
                return await Service.addNguoiDung(nguoidung);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        #region PUT

        /// <summary>
        /// PUT nguoi dung
        /// </summary>
        /// <data>nguoidung</data>
        /// <return></return>
        [ResponseType(typeof(ttNguoiDung))]
        [Route("nguoi-dung")]
        [HttpPut]
        public async Task<bool> putNhaTro(ttNguoiDung nguoidung)
        {
            try
            {
                return await Service.updateNguoiDung(nguoidung);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        //---------------------------------------------//





        //---------------BINH LUAN ------------------//

        #region GET

        /// <summary>
        /// GET danh sach binh luan theo id nha tro
        /// </summary>
        /// <return></return>
        [Route("binh-luan")]
        [HttpGet]
        public async Task<IEnumerable<BinhLuan>> getBinhLuan(string id)
        {
            try
            {
                return await Service.listBinhLuan(id);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

        #region POST

        /// <summary>
        /// POST binh luan
        /// </summary>
        /// <data>binhluan</data>
        /// <return></return>
        [ResponseType(typeof(ttBinhLuan))]
        [Route("binh-luan")]
        [HttpPost]
        public async Task<bool> postBinhLuan(ttBinhLuan binhluan)
        {
            try
            {
                return await Service.addBinhLuan(binhluan);
            }
            catch (Exception e)
            {
                throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.BadRequest, false));
            }
        }

        #endregion

    }
}