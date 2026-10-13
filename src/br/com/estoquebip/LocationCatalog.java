package br.com.estoquebip;
import java.io.*;
import java.util.*;
/** Exact membership in the supplied location bases, with trimmed case-insensitive codes. */
final class LocationCatalog {
 private final Set<String> codes=new HashSet<>();
 LocationCatalog(Reader input) throws IOException {try(BufferedReader reader=new BufferedReader(input)){String line;while((line=reader.readLine())!=null){String value=normalize(line);if(!value.isEmpty())codes.add(value);}}if(codes.isEmpty())throw new IOException("Base de locais vazia");}
 static String normalize(String value){return value==null?"":value.replace('\u00a0',' ').trim().toUpperCase(Locale.ROOT);}
 boolean contains(String value){return codes.contains(normalize(value));}
 int size(){return codes.size();}
}
