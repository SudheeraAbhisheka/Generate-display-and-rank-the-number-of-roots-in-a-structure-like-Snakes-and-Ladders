import java.util.*;
import java.io.*;

public class WritingToAFile
{
    public void writingAFile(String pFilename, Object[] heapEntry, int[] heapPriority) 
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        DSAStack[] stackArray;
        DSAGraphVertex v;

        try
        {
            fileStrm = new FileOutputStream(pFilename);
            pw = new PrintWriter(fileStrm);	 

            stackArray = new DSAStack[heapEntry.length];
            for(int i = 0; i < heapEntry.length; i++){
                stackArray[i] = (DSAStack)heapEntry[i];
            }

            for(int i = 0; i < heapEntry.length; i++){
                pw.print("Rank: " + i + "   ");
                for (Object o : stackArray[i]){
                    v = (DSAGraphVertex)o;
                    pw.print(v.getLabel() + " ");
                }
            
            pw.println("    Units = " + heapPriority[i]);
        }

            pw.close();					 
        }
        catch(IOException e)
        {			 
            System.out.println("Error in writing to file: " + e.getMessage());
        }   
    }

    public void saveMatrix(String pFilename, Object[][] matrixArray) 
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {
            fileStrm = new FileOutputStream(pFilename);
            pw = new PrintWriter(fileStrm);	 

            for(int l = 0; l < matrixArray.length; l++){
                for(int k = 0; k < matrixArray.length; k++){
                    pw.print(String.format("%10s", matrixArray[l][k]));
                }
                pw.println();
            }

            pw.close();					 
        }
        catch(IOException e)
        {			 
            System.out.println("Error in writing to file: " + e.getMessage());
        }   
    }

    public void saveNetwork(String pFilename, Object[][] networkArray) 
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {
            fileStrm = new FileOutputStream(pFilename);
            pw = new PrintWriter(fileStrm);	 

            for(int l = 0; l < networkArray.length; l++){
                for(int k = 0; k < networkArray[0].length; k++){
                    pw.print(networkArray[l][k] + " ");
                }
                pw.println();
            }

            pw.close();					 
        }
        catch(IOException e)
        {			 
            System.out.println("Error in writing to file: " + e.getMessage());
        }   
    }
}
