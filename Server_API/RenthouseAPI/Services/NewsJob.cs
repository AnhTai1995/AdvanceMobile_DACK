using Quartz;
using RenthouseAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Http;


namespace RenthouseAPI.Services
{
    public class NewsJob : IJob
    {
        private NHATROEntities db = new NHATROEntities();
        
        public void Execute(IJobExecutionContext context)
        {
            Services.ServicesDB service = new ServicesDB();
            service.listNhaTro123(1);
        }
    }
}