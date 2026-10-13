package br.com.estoquebip;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
/** Per-product factors already expressed as pieces, including the PL factor. */
final class PackCatalog {
 static final class Product {final String code,name;final BigDecimal box,pallet;Product(String code,String name,String box,String pallet){this.code=code;this.name=name;this.box=box.isEmpty()?null:new BigDecimal(box);this.pallet=pallet.isEmpty()?null:new BigDecimal(pallet);}BigDecimal factor(String unit){return "CX".equals(unit)?box:"PL".equals(unit)?pallet:BigDecimal.ONE;}}
 private final Map<String,Product> items=new HashMap<>();
 PackCatalog(Reader input) throws IOException {try(BufferedReader reader=new BufferedReader(input)){String line;while((line=reader.readLine())!=null){String[] fields=line.split("\t",-1);if(fields.length!=5)throw new IOException("Linha inválida no cadastro de packs");Product product=new Product(fields[0],fields[2],fields[3],fields[4]);items.put(key(fields[0]),product);for(String alias:fields[1].split(","))if(!alias.isEmpty())items.put(key(alias),product);}}if(items.isEmpty())throw new IOException("Base de packs vazia");}
 static String key(String value){return value==null?"":value.replace('\u00a0',' ').trim().toUpperCase(Locale.ROOT);}
 Product find(String code){return items.get(key(code));}
 static boolean exceedsPack(BigDecimal extra,BigDecimal pack){return extra.compareTo(pack)>0;}
 static BigDecimal totalPieces(BigDecimal count,BigDecimal pack,BigDecimal extra){return count.multiply(pack).add(extra);}
}
