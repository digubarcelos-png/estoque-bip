package br.com.estoquebip;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
final class BarcodeDecoder {
 static Result decode(MultiFormatReader reader,byte[] data,int width,int height,float[] region){
  int[] box=ScanGeometry.crop(width,height,region);
  PlanarYUVLuminanceSource source=new PlanarYUVLuminanceSource(data,width,height,box[0],box[1],box[2],box[3],false);
  for(int turn=0;turn<2;turn++){
   try{return reader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source)));}
   catch(ReaderException e){byte[] rotated=ScanGeometry.rotate(source.getMatrix(),source.getWidth(),source.getHeight());source=new PlanarYUVLuminanceSource(rotated,source.getHeight(),source.getWidth(),0,0,source.getHeight(),source.getWidth(),false);}
   finally{reader.reset();}
  }
  return null;
 }
}
