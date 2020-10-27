package org.idpass.lite;

import com.google.protobuf.ByteString;
import io.mosip.kernel.core.util.CryptoUtil;
import org.api.proto.Ident;
import org.api.proto.KV;
import org.idpass.lite.exceptions.IDPassException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.github.jaiimageio.jpeg2000.impl.J2KImageReader;

@Component
public class IDPassReaderComponent
{
    public IDPassReader reader;

    public IDPassReaderComponent()
            throws IDPassException, IOException
    {
        reader = new IDPassReader();
        reader.setDetailsVisible(
                IDPassReader.DETAIL_GIVENNAME |
                IDPassReader.DETAIL_SURNAME |
                IDPassReader.DETAIL_PLACEOFBIRTH);
    }

    public byte[] generateQrCode(String cs, String pincode, String photob64)
    {
        Ident.Builder identBuilder = Ident.newBuilder()
                .setPin(pincode);

        String imageType = photob64.split(",")[0];
        byte[] photo = CryptoUtil.decodeBase64(photob64.split(",")[1]);
        photo = convertToJPG(photo);

        JSONObject credentialSubject = new JSONObject(cs);

        if (photo != null) {
            identBuilder.setPhoto(ByteString.copyFrom(photo));
        }

        Iterator<String> keys = credentialSubject.keySet().iterator();

        while (keys.hasNext()) {
            String keyStr = keys.next();
            String strVal = (String)credentialSubject.get(keyStr);
            identBuilder.addPubExtra(KV.newBuilder().setKey(keyStr).setValue(strVal));
        }

        Ident ident = identBuilder.build();
        byte[] qrcodeId = null;

        try {
            Card card = reader.newCard(ident, null);
            BufferedImage bi = card.asQRCode();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", bos);
            qrcodeId = bos.toByteArray();

        } catch (IOException | IDPassException e) {
            e.printStackTrace();
        }

        return  qrcodeId;
    }

    // Notes: copied from 'mosip-functional-tests' repo
    private static byte[] convertToJPG(byte[] jp2Data) {
        byte[] jpgImg = null;
        J2KImageReader j2kImageReader = new J2KImageReader(null);
        try {
            j2kImageReader.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(jp2Data)));
            ImageReadParam imageReadParam = j2kImageReader.getDefaultReadParam();
            BufferedImage image = j2kImageReader.read(0, imageReadParam);
            ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
            ImageIO.write(image, "JPG", imgBytes);
            jpgImg = imgBytes.toByteArray();
        } catch (IOException e) {

        }

        return jpgImg;
    }
}
