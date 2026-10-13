package br.com.estoquebip;
import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
public final class CoreChecks {
 static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
 public static void main(String[] args)throws Exception{
 LocationCatalog locations=new LocationCatalog(new InputStreamReader(new FileInputStream("assets/locais_validos.txt"),"UTF-8"));
 check(locations.size()==39297,"location count");check(locations.contains(LocationCatalog.normalize(" 001c05a ")),"location normalization");check(!locations.contains("INVALID-LOCATION"),"invalid location");
 PackCatalog catalog=new PackCatalog(new InputStreamReader(new FileInputStream("assets/packs_produtos.tsv"),"UTF-8"));int products=0;
 try(BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("assets/packs_produtos.tsv"),"UTF-8"))){String line;while((line=reader.readLine())!=null){String[] f=line.split("\t",-1);PackCatalog.Product p=catalog.find(f[0]);check(p!=null&&p.name.equals(f[2]),"product lookup");for(String alias:f[1].split(","))if(!alias.isEmpty())check(catalog.find(alias)==p,"alias lookup");for(String unit:new String[]{"CX","PL"}){BigDecimal factor=p.factor(unit);if(factor!=null){check(!PackCatalog.exceedsPack(factor,factor),"equal pack permitted");check(PackCatalog.exceedsPack(factor.add(BigDecimal.ONE),factor),"excess rejected");check(PackCatalog.totalPieces(new BigDecimal("2"),factor,new BigDecimal("3")).compareTo(factor.multiply(new BigDecimal("2")).add(new BigDecimal("3")))==0,"piece total");}}products++;}}
 check(products==86768,"product count");check(Arrays.equals(ScanGeometry.rotate(new byte[]{1,2,3,4,5,6},3,2),new byte[]{4,1,5,2,6,3}),"frame rotation");int[] crop=ScanGeometry.crop(640,480,new float[]{-.1f,-.1f,1.1f,1.1f});check(Arrays.equals(crop,new int[]{0,0,640,480}),"crop clamp");
 for(com.google.zxing.BarcodeFormat format:new com.google.zxing.BarcodeFormat[]{com.google.zxing.BarcodeFormat.CODE_128,com.google.zxing.BarcodeFormat.QR_CODE}){String code="001C05A";com.google.zxing.common.BitMatrix matrix=new com.google.zxing.MultiFormatWriter().encode(code,format,400,200);byte[] pixels=new byte[80000];for(int y=0;y<200;y++)for(int x=0;x<400;x++)pixels[y*400+x]=(byte)(matrix.get(x,y)?0:255);com.google.zxing.Result result=BarcodeDecoder.decode(new com.google.zxing.MultiFormatReader(),pixels,400,200,new float[]{0,0,1,1});check(result!=null&&code.equals(result.getText()),"barcode decode "+format);}
 System.out.println("PASS: leitura CODE_128 e QR; 39.297 locais, 86.768 produtos, aliases, limites CX/PL, totais e geometria da câmera.");
 }
}
