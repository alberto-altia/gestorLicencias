package proyectoFCT.gestorLicencias.controller.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;


@RestController
public class GeneradorJWTController {

    private static final Logger LOG = null;

    @GetMapping(value = "/generador/tokenJWT")
    public String generarJWT(@RequestParam("usuario") String usuario) {

        LOG.info("Vamos a generar el token");

        String token = null;

        String semilla = "5357815dce3d72e19a6455e9217a51a26016d427";
        String subject = ""; // Identificador del usuario de flexia (NIF)
        String issuer = ""; // Entidad para la que está loggeado el usuario en este momento
        Map<String, Object> customClaims = null;
        int validezMs = 120000;

        JWSSigner signer = new MACSigner(semilla.getBytes(StandardCharsets.UTF_8));

        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet();
            claimsSet.setSubject(subject);
            claimsSet.setIssuer(issuer);
            claimsSet.setExpirationTime(new Date(System.currentTimeMillis() + validezMs));
            claimsSet.setCustomClaims(customClaims);

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);
            token = signedJWT.serialize();
            LOG.info("METHOD generateJWT () END RETURNING [" + token + "]");

        } catch (JOSEException e1) {
            LOG.error("Error al generar el token", e1);
        } catch (Exception e) {
            LOG.error("Error al generar el token", e);
        }

        return token;
    }
}