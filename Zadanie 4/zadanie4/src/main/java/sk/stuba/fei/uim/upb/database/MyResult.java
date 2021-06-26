package sk.stuba.fei.uim.upb.database;

public class MyResult {
	
	 private final boolean first;
     private final String msg;
     
     public MyResult(boolean first, String second) {
         this.first = first;
         this.msg = second;
     }
     public boolean getFirst() {
         return first;
     }
     public String getMsg() {
         return msg;
     }
}
