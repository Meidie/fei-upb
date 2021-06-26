package sk.stuba.fei.uim.upb.database;

public class MyResult {
	
	 private final boolean success;
     private final String msg;
     
     public MyResult(boolean success, String msg) {
         this.success = success;
         this.msg = msg;
     }

     public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
         return msg;
     }
}
