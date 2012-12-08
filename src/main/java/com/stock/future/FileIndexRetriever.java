package com.stock.future;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileIndexRetriever implements IndexRetriever {
    Pattern p = Pattern.compile( "\\d{3,5}.\\d{1,2}" );
    Matcher m;
    
    public FileIndexRetriever() throws IOException {
        InputStream is = FileIndexRetriever.class.getClassLoader().getResourceAsStream( "Table.txt" );
        BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
        String value = br.readLine();
        m = p.matcher( value );
    }

    public double getCurrentIndex() {        
        if (m.find() ) {
            return Double.parseDouble( m.group() );
        } 
        return 0.0;
    }
    
    public static void main(String[] args) throws Exception {        
        IndexRetriever ir = new FileIndexRetriever();
        while (true) {
            double index = ir.getCurrentIndex();
            if ( index < 1 ) {
                break;
            }
            System.out.println( index );
        }
    }

}
