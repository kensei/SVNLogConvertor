package jp.co.bcc.SVNLogConvertor;

/**
 * 
 * Main
 * 
 * @author BCC
 *
 */
public class Main 
{
	/**
	 * 
	 * @param args
	 */
    public static void main( String[] args )
    {
    	LogConvertor conv;
		try {
			conv = new LogConvertor();
			conv.init();
			conv.entry();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
