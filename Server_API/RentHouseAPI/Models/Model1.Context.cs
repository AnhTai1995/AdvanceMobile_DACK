﻿//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace RenthouseAPI.Models
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class NHATROEntities : DbContext
    {
        public NHATROEntities()
            : base("name=NHATROEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public DbSet<AccountType> AccountTypes { get; set; }
        public DbSet<BinhLuan> BinhLuans { get; set; }
        public DbSet<ChiTietNhaTro> ChiTietNhaTroes { get; set; }
        public DbSet<NguoiDung> NguoiDungs { get; set; }
        public DbSet<NhaTro> NhaTroes { get; set; }
        public DbSet<NhaTroDaLuu> NhaTroDaLuus { get; set; }
    }
}
