/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * TO DO **********
 *New Tiles
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Queue;
import java.util.LinkedList;
 /*
 * @author joshlaverghetta
 */
public class ThreesAttempt {
       static LinkedList<String> nums = new LinkedList<>();
       static int[][] board = new int[4][4];
    public static void readFile() {
       
        try {
            Scanner sc = new Scanner(new FileReader("exampleinput.txt"));
           // sc.useDelimiter("\\t");
            sc.nextLine();
            sc.nextLine();
            int z=0;
            while (sc.hasNext() && z<20) {
                
                    nums.add(sc.next());
                    z++;
                
            }
           /* for (int i =0; i<nums.size();i++){
                System.out.println(nums.remove());
            }*/
        
            
         
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       
    }
    public static String setBoard(int r,int c){
        String Text="";
        if(board[r][c]!=0)
            Text=Text+board[r][c];
        return Text;
    }
    
    public static void set(){
        int k=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                try{
                    
                      board[i][j]=Integer.parseInt(nums.get(k));

                }catch(NumberFormatException n){
                    board[i][j]=-1;
                }
                k++;
            }
        }
       
    }
    public static String countScore(){
    	String score="";
    	int count=0;
    	for (int i=0;i<4;i++){
    		for (int j=0;j<4;j++){
    			if(board[i][j]==1||board[i][j]==2){
    				count+=1;
    			}else
    			count+=board[i][j];
    		}
    	}
    	score+=count;
    	return score;
    }
    public static void printArr (){
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                System.out.println(board[i][j]);
            }
        }
    }
    public static void mess (){
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                board[i][j]=-1;
            }
        }
    }
    public static boolean Left(){
    	boolean did=false;
        for (int i=0; i<4;i++){
        	if(board[i][0]==1 && board[i][1]==2 ||board[i][0]==2 && board[i][1]==1 ){
        		board[i][0]=3;
        		board[i][1]=board[i][2];
                board[i][2]=board[i][3];
                board[i][3]=0;
                did=true;
        	}
        	else if(board[i][0]==board[i][1]&&board[i][1]>2){
                did=true;
                board[i][0]*=2;
                board[i][1]=board[i][2];
                board[i][2]=board[i][3];
                board[i][3]=0;
            }else if((board[i][2]>2&&board[i][0]!=0)&&board[i][1]==board[i][2]){
            	board[i][1]*=2;
	            board[i][2]=board[i][3];
	            board[i][3]=0;
	            did=true;
            }else if((board[i][2]!=0&&board[i][0]!=0)&&(board[i][1]==1 &&board[i][2]==2||board[i][1]==2 &&board[i][2]==1)){
            	board[i][1]=3;
	            board[i][2]=board[i][3];
	            board[i][3]=0;
            	did=true;
            }else if(board[i][3]>2&&board[i][1]!=0&&board[i][0]!=0){
            	if(board[i][2]==board[i][3]){
            		board[i][2]*=2;
            		board[i][3]=0;
                	did=true;
            	}else if (board[i][2]==1&&board[i][3]==2||board[i][2]==2&&board[i][3]==1){
            		board[i][2]=3;
            		board[i][3]=0;
            		did=true;
            	}
            }
    	
            
            for(int j=0; j<3;j++){
            	if(board[i][j]==0&& board[i][j+1]!=0){
            		board[i][j]=board[i][j+1];
            		board[i][j+1]=0;
            		did=true;
            	}
            }
            
            
        }
        return did;
    }
    public static  boolean Right(){
        int k=3;
        boolean did =false;
        for (int i=0; i<4;i++){
        	
            if(board[i][k-0]==board[i][k-1]&&board[i][k-0]>2){
                did=true;
                board[i][k-0]*=2;
                board[i][k-1]=board[i][k-2];
                board[i][k-2]=board[i][k-3];
                board[i][k-3]=0;
            }else if(board[i][k-0]==1 && board[i][k-1]==2||board[i][k-0]==2 && board[i][k-1]==1){
            	board[i][k-0]=3;
                board[i][k-1]=board[i][k-2];
                board[i][k-2]=board[i][k-3];
                board[i][k-3]=0;
            	did=true;
            }else if((board[i][k-1]>2&&board[i][k]!=0)&&board[i][k-1]==board[i][k-2]){
            	did=true;
	            board[i][k-1]*=2;
	            board[i][k-2]=board[i][k-3];
	            board[i][k-3]=0;
            }else if((board[i][k-1]!=0&&board[i][k]!=0)&&board[i][k-1]==1&&board[i][k-2]==2||board[i][k-1]==2&&board[i][k-2]==1){
            	board[i][k-1]=3;
	            board[i][k-2]=board[i][k-3];
	            board[i][k-3]=0;
            	did=true;
            }else if(board[i][k-3]>2&&board[i][k-1]!=0&&board[i][k]!=0){
            	
            	if(board[i][k-2]==board[i][k-3]){
            		board[i][k-2]*=2;
            		board[i][k-3]=0;
            		did=true;
            	}else if((board[i][k-2]==2&&board[i][k-3]==1)||(board[i][k-2]==1&&board[i][k-3]==2)){
            		did=true;
            		board[i][k-2]=3;
            		board[i][k-3]=0;
            	}
            }
            for (int j=3;j>0;j--){
                if(board[i][j]==0 && board[i][j-1]!=0 ){
                board[i][j]=board[i][j-1];
                board[i][j-1]=0;
                did=true;
                }
            }
            
            
        }
        return did;
        
    }
     public static boolean Up(){
    	 boolean did =false;
        for (int i=0; i<4;i++){
            if(board[0][i]==board[1][i]&&board[0][i]>2){
                
                board[0][i]*=2;
                board[1][i]=board[2][i];
                board[2][i]=board[3][i];
                board[3][i]=0;
                did=true;
            }else if(board[0][i]==1&&board[1][i]==2||board[0][i]==2&&board[1][i]==1){
            	board[0][i]=3;
                board[1][i]=board[2][i];
                board[2][i]=board[3][i];
                board[3][i]=0;
                did=true;
            }else if(board[1][i]==board[2][i]&&board[1][i]>2&&board[0][i]!=0){
                board[1][i]*=2;
                board[2][i]=board[3][i];
                board[3][i]=0;
                did=true;
            }else if((board[1][i]!=0&&board[0][i]!=0)&&(board[1][i]==1&& board[2][i]==2||board[1][i]==2&&board[2][i]==1)){
            	board[1][i]=3;
                board[2][i]=board[3][i];
                board[3][i]=0;
            	did=true;
            }else if(board[2][i]==board[3][i]&&board[2][i]>2&&board[1][i]!=0&&board[0][i]!=0){
                board[2][i]*=2;
                board[3][i]=0;
                did=true;
            }else if((board[2][i]!=0&&board[1][i]!=0&&board[0][i]!=0)&&(board[2][i]==1&&board[3][i]==2||board[2][i]==2&&board[3][i]==1)){
            	board[2][i]=3;
            	board[3][i]=0;
            	did=true;
            }
            for (int j=0;j<3;j++){
                if(board[j][i]==0 && board[j][i]!=0){
                board[j][i]=board[j+1][i];
                board[j+1][i]=0;
                did=true;
                }
            }
            
           
            
            
        }
        return did;
        
    }
       public static boolean Down(){
           int k=3;
           boolean did=false;
        for (int i=0; i<4;i++){
        	
            if(board[k-0][i]==board[k-1][i]&&board[k][i]>2){ //check if = 
                board[k-0][i]*=2;
                board[k-1][i]=board[k-2][i];
                board[k-2][i]=board[k-3][i];
                board[k-3][i]=0;
                did=true;
            }else if(board[k-0][i]==1&&board[k-1][i]==2||board[k][i]==2&&board[k-1][i]==1){
            	board[k-0][i]=3;
                board[k-1][i]=board[k-2][i];
                board[k-2][i]=board[k-3][i];
                board[k-3][i]=0;
            	did=true;
            }else if(board[k-1][i]==board[k-2][i]&&board[k][i]!=0&&board[k-1][i]>2){
                board[k-1][i]*=2;
                board[k-2][i]=board[k-3][i];
                board[k-3][i]=0;
                did=true;
            }else if((board[k][i]!=0&&board[k-1][i]!=0)&&(board[k-1][i]==1&&board[k-2][i]==2||board[k-1][i]==2&&board[k-2][i]==1)){
            	board[k-1][i]=3;
                board[k-2][i]=board[k-3][i];
                board[k-3][i]=0;
            	did=true;
            }else if(board[k-2][i]==board[k-3][i]&&board[k][i]!=0&&board[k-1][i]!=0&&board[k-2][i]>2){
                board[k-2][i]*=2;
                board[k-3][i]=0;
                did=true;
            }else if((board[k][i]!=0&&board[k-1][i]!=0&&board[k-2][i]!=0)&&((board[k-2][i]==1&&board[k-3][i]==2||board[k-2][i]==2&&board[k-3][i]==1))){
            	board[k-2][i]=3;
                board[k-3][i]=0;
                did=true;
            }
            for (int j=3;j>0;j--){
                if(board[j][i]==0 && board[j-1][i] !=0){
                board[j][i]=board[j-1][i];
                board[j-1][i]=0;
                did=true;
                }
            }
            
            
        }
        return did;
        
    }
   
    /**
     * @param args the command line arguments
    
    public static void main(String[] args) {
     //   System.out.println(readFile());
        readFile();
        set();
        printArr();
    }*/

}
