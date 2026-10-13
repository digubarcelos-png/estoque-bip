package br.com.estoquebip;
/** Luminance frame rotation and normalized viewfinder coordinates. */
final class ScanGeometry {
 static byte[] rotate(byte[] data,int width,int height){byte[] rotated=new byte[width*height];for(int y=0;y<height;y++)for(int x=0;x<width;x++)rotated[x*height+(height-y-1)]=data[y*width+x];return rotated;}
 static int[] crop(int width,int height,float[] region){int x=Math.max(0,Math.min(width-1,(int)(region[0]*width))),y=Math.max(0,Math.min(height-1,(int)(region[1]*height)));int right=Math.max(x+1,Math.min(width,(int)(region[2]*width))),bottom=Math.max(y+1,Math.min(height,(int)(region[3]*height)));return new int[]{x,y,right-x,bottom-y};}
}
