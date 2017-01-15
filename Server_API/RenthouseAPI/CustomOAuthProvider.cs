using System.Threading.Tasks;
using Microsoft.Owin.Security.OAuth;
using System.Security.Claims;
using Microsoft.Owin.Security;
using System.Collections.Generic;
using RenthouseAPI.Services;
using RenthouseAPI.Models;
using System.Linq;

namespace RenthouseAPI
{
    public class CustomOAuthProvider : OAuthAuthorizationServerProvider
    {
        private NHATROEntities db = new NHATROEntities();
        private ServicesDB Service = new ServicesDB();

        public override Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            string clientId = string.Empty;
            string clientSecret = string.Empty;
            string symetricKeyAsBase64 = string.Empty;

            if (!context.TryGetBasicCredentials(out clientId, out clientSecret))
            {
                context.TryGetFormCredentials(out clientId, out clientSecret);
            }

            if(context.ClientId == null)
            {
                context.SetError("invalid_clientId", "client_Id is not set");
                return Task.FromResult<object>(null);
            }

            var audience = AudiencesStore.FindAudience(context.ClientId);

            if (audience == null)
            {
                context.SetError("invalid_clientId", string.Format("Invalid client_id '{0}'", context.ClientId));
                return Task.FromResult<object>(null);
            }

            context.Validated();
            return Task.FromResult<object>(null);
        }

        public override Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            context.OwinContext.Response.Headers.Add("Access-Control-Allow-Origin", new[] { "*" }); 

            if (Service.checkAcc(context.UserName, context.Password) == false)
            {
                context.SetError("invalid_grant", "The user name or password is incorrect");

                return Task.FromResult<object>(null);
            }

            var identity = new ClaimsIdentity("JWT");

            var q = (from e in db.NguoiDungs
                     join k in db.AccountTypes on e.AccountType equals k.ID
                     where context.UserName == e.Username
                     select k).FirstOrDefault();
            if (q.TenLoai == "ACCOUNT ADMIN")
            {
                identity.AddClaim(new Claim(ClaimTypes.Role, "Manager"));
            }
            else
                identity.AddClaim(new Claim(ClaimTypes.Role, "User"));

            var props = new AuthenticationProperties(new Dictionary<string, string>
            {
                {
                    "audience", (context.ClientId == null) ? string.Empty : context.ClientId
                }
            });

            var ticket = new AuthenticationTicket(identity, props);
            context.Validated(ticket);
            return Task.FromResult<object>(null);
        }
    }
}