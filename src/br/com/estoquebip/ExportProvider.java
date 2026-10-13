package br.com.estoquebip;
import android.content.*;
import android.database.*;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import java.io.*;

/** Only generated CSV exports can be read through a temporary URI grant. */
public class ExportProvider extends ContentProvider {
 public boolean onCreate(){return true;}
 private File file(Uri uri) throws FileNotFoundException {
  String name=uri.getLastPathSegment();
  if(!"br.com.estoquebip.exports".equals(uri.getAuthority())||uri.getPathSegments().size()!=1||name==null||!name.matches("EstoqueBip_[0-9]{8}_[0-9]{6}_[0-9]+\\.csv"))throw new FileNotFoundException("Exportação inválida");
  File f=new File(new File(getContext().getCacheDir(),"exports"),name);
  if(!f.isFile())throw new FileNotFoundException("Arquivo indisponível");return f;
 }
 public ParcelFileDescriptor openFile(Uri uri,String mode) throws FileNotFoundException {if(!"r".equals(mode))throw new FileNotFoundException("Somente leitura");return ParcelFileDescriptor.open(file(uri),ParcelFileDescriptor.MODE_READ_ONLY);}
 public String getType(Uri uri){return "text/csv";}
 public Cursor query(Uri uri,String[] projection,String selection,String[] args,String order){try{File f=file(uri);String[] columns=projection==null?new String[]{OpenableColumns.DISPLAY_NAME,OpenableColumns.SIZE}:projection;MatrixCursor cursor=new MatrixCursor(columns);Object[] row=new Object[columns.length];for(int i=0;i<columns.length;i++)row[i]=OpenableColumns.DISPLAY_NAME.equals(columns[i])?f.getName():OpenableColumns.SIZE.equals(columns[i])?f.length():null;cursor.addRow(row);return cursor;}catch(FileNotFoundException e){return null;}}
 public Uri insert(Uri u,ContentValues v){throw new UnsupportedOperationException();}public int delete(Uri u,String s,String[] a){throw new UnsupportedOperationException();}public int update(Uri u,ContentValues v,String s,String[] a){throw new UnsupportedOperationException();}
}
