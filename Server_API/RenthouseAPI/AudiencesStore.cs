using Microsoft.Owin.Security.DataHandler.Encoder;
using RenthouseAPI.Entities;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Web;

namespace RenthouseAPI
{
    public static class AudiencesStore
    {
        public static ConcurrentDictionary<string, Audience> AudienceList = new ConcurrentDictionary<string, Audience>();
        static AudiencesStore()
        {
            AudienceList.TryAdd("099153c2625149bc8ecb3e85e03f0022", 
                new Audience {
                    ClientId = "099153c2625149bc8ecb3e85e03f0022",
                    Base64Secret = "IxrAjDoa2FqElO7IhrSrUJELhUckePEPVpaePlS_Xaw",
                    Name = "ResourceServer.Api 1"
                });
        }

        public static Audience AddAudience(string name)
        {
            var clientId = Guid.NewGuid().ToString("N");

            var key = new byte[32];
            RNGCryptoServiceProvider.Create().GetBytes(key);
            var base64Secret = TextEncodings.Base64Url.Encode(key);

            Audience newAudience = new Audience
            {
                ClientId = clientId,
                Base64Secret = base64Secret,
                Name = name
            };
            AudienceList.TryAdd(clientId, newAudience);
            return newAudience;
        }

        public static Audience FindAudience(string clientId)
        {
            Audience audience = null;
            if (AudienceList.TryGetValue(clientId, out audience)) ;
            {
                return audience;
            }
            return null;
        }
    }
}   