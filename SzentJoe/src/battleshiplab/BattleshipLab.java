
package battleshiplab;

import java.util.Random;
import java.util.Scanner;

public class BattleshipLab {

    public static void main(String[] args) {
    
        int[][] board = new int[6][6];
        int[][] ships = new int[5][5];
        int[] shoot = new int[2];
        int attempts=0,
            shotHit=0;
        
        initBoard(board);
        initShips(ships);
        
        System.out.println();
        
        do{
            showBoard(board);
            shoot(shoot);
            attempts++;
            
            if(hit(shoot,ships)){
                hint(shoot,ships,attempts);
                shotHit++;
            }                
            else
                hint(shoot,ships,attempts);
            
            changeboard(shoot,ships,board);
            

        }while(shotHit!=5);
        
        System.out.println("\n\n\nVéget ért a játék! Lelőttél 3 halyót "+attempts+" próbálkozással!");
        showBoard(board);
    }
    
    public static void initBoard(int[][] board){
        for(int row=0 ; row < 6 ; row++ )
            for(int column=0 ; column < 6 ; column++ )
                board[row][column]=-1;
    }
    
    public static void showBoard(int[][] board){
        System.out.println("\tA \tB \tC \tD \tE \tF");
        System.out.println();
        
        for(int row=0 ; row < 6 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 6 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    System.out.print("\t"+"X");
                }
                
            }
            System.out.println();
        }

    }

    public static void initShips(int[][] ships){
        Random random = new Random();
        
        for(int ship=0 ; ship < 5 ; ship++){
            ships[ship][0]=random.nextInt(6);
            ships[ship][1]=random.nextInt(6);
            
            //let's check if that shot was already tried 
            //if it was, just finish the do...while when a new pair was randomly selected
            for(int last=0 ; last < ship ; last++){
                if( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
                    do{
                        ships[ship][0]=random.nextInt(6);
                        ships[ship][1]=random.nextInt(6);
                    }while( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
            }
            
        }
    }

    public static void shoot(int[] shoot){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Sor: ");
        shoot[0] = input.nextInt();
        shoot[0]--;
        
        System.out.print("Oszlop: ");
        shoot[1] = input.nextInt();
        shoot[1]--;
        
    }
    
    public static boolean hit(int[] shoot, int[][] ships){
        
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                System.out.printf("Lelőtted a hajót itt: (%d,%d)\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
    }

    public static void hint(int[] shoot, int[][] ships, int attempt){
        int row=0,
            column=0;
        
        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==shoot[0])
                row++;
            if(ships[line][1]==shoot[1])
                column++;
        }
        
        System.out.printf("\nPróbálkozások %d: \nEltalált hajók száma: %d -> %d \n" +
                                 "Oszlop %d -> %d hajó\n",attempt,shoot[0]+1,row,shoot[1]+1,column);
    }

    public static void changeboard(int[] shoot, int[][] ships, int[][] board){
        if(hit(shoot,ships))
            board[shoot[0]][shoot[1]]=1;
        else
            board[shoot[0]][shoot[1]]=0;
    }
}
