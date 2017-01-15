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
        public async Task<IEnumerable<NhaTro>> getNhaTro()
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
        /// <param>NhaTro</param>
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

        #endregion

        #region PUT



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
        public async Task<IEnumerable<NguoiDung>> getNguoiDung()
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

    }
}