

package Service;

import java.util.Scanner;

public class Operacion {
    

    public double[][] sumarM(double[][] matriz, double [][] y){
        double [][] sumarM;
        int limite;
        Scanner lector = new Scanner (System.in);
        System.out.println("Ingrese el tamaño de las matrices:");
        limite = lector.nextInt();
       
        sumarM = new double[limite][limite];

       // sumarM[0][0] = x[0][0] + y[0][0];
       // sumarM[0][1] = x[0][1] + y[0][1];
       // sumarM[0][2] = x[0][2] + y[0][2];
       // sumarM[1][0] = x[1][0] + y[1][0];
       // sumarM[1][1] = x[1][1] + y[1][1];
       // sumarM[1][2] = x[1][2] + y[1][2];
       // sumarM[2][0] = x[2][0] + y[2][0];
       // sumarM[2][1] = x[2][1] + y[2][1];
       // sumarM[2][2] = x[2][2] + y[2][2];
    
        for (int i = 0; i < limite; i++) {
            for (int j = 0; j < limite; j++) {
                sumarM[i][j] = matriz[i][j]+ y[i][j];                
            }           
        }
        // muestra en pantalla la primera matriz:
        for (int i = 0; i < limite; i++) {            
            for (int j = 0; j < limite; j++) {
            System.out.printf(matriz[i][j]+"\t");  
            }   
            System.out.println();
        }   System.out.println("--------------------"); 
        // muestra en pantalla la segunda matriz:
        for (int i = 0; i < limite; i++) {            
            for (int j = 0; j < limite; j++) {
            System.out.printf(y[i][j]+"\t");  
            }   
            System.out.println();
        }   System.out.println("--------------------");
        
        // operacion de suma de matrices
        
        for (int i = 0; i < limite; i++) {            
            for (int j = 0; j < limite; j++) {
            System.out.printf(sumarM[i][j]+"\t");  
            }   
            System.out.println();
        }        
        return sumarM;
    }
    
    
    public double[][] productoM(double[][] matriz, double[][] y){
        double[][] productoM;
        int limite;
        Scanner lector = new Scanner (System.in);
        System.out.println("Ingrese el tamaño de las matrices:");
        limite = lector.nextInt();
    
        productoM = new double[limite][limite];
        
        //productoM[0][0] = x[0][0]*y[0][0] + x[0][1]*y[1][0] + x[0][2]*y[2][0];
        //productoM[0][1] = x[0][0]*y[0][1] + x[0][1]*y[1][1] + x[0][2]*y[2][1];
        //productoM[0][2] = x[0][0]*y[0][2] + x[0][1]*y[1][2] + x[0][2]*y[2][2];
        //productoM[1][0] = x[1][0]*y[0][0] + x[1][1]*y[1][0] + x[1][2]*y[2][0];
        //productoM[1][1] = x[1][0]*y[0][1] + x[1][1]*y[1][1] + x[1][2]*y[2][1];
        //productoM[1][2] = x[1][0]*y[0][2] + x[1][1]*y[1][2] + x[1][2]*y[2][2];
        //productoM[2][0] = x[2][0]*y[0][0] + x[2][1]*y[1][0] + x[2][2]*y[2][0];
        //productoM[2][1] = x[2][0]*y[0][1] + x[2][1]*y[1][1] + x[2][2]*y[2][1];
        //productoM[2][2] = x[2][0]*y[0][2] + x[2][1]*y[1][2] + x[2][2]*y[2][2];
        
        //Llenar matriz con resultado de multiplicacion
        for (int i = 0; i <limite; i++) {
            for (int j = 0; j < limite; j++) {
                for (int k = 0; k < limite; k++) {
                    productoM[i][j] += matriz[i][k]*y[k][j];
                }
                
            }
            
        }
        // imprimir Resultado
        for (int i = 0; i < limite; i++) {           
            for (int j = 0; j < limite; j++) {
            System.out.printf(productoM[i][j]+"\t");  
            } 
            System.out.println();
        }    
    
    return productoM;
    }
    
    public double detM (double[][] matriz){
     
    double det;
    if(matriz.length==2)
    {
        det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
        return det;
    }
    double suma=0;
    for(int i=0; i<matriz.length; i++){
    double[][] nm=new double[matriz.length-1][matriz.length-1];
        for(int j=0; j<matriz.length; j++){
            if(j!=i){
                for(int k=1; k<matriz.length; k++){
                int indice=-1;
                if(j<i)
                indice=j;
                else if(j>i)
                indice=j-1;
                nm[indice][k-1]=matriz[j][k];
                }
            }
        }
        if(i%2==0)
        suma+=matriz[i][0] * detM(nm);
        else
        suma-=matriz[i][0] * detM(nm);
    }
    System.out.println(+suma);
    return suma;
    }
 // inversa de una matriz   
public double[][] matrizInversa(double[][] matriz) {
    
    double det=1/determinante(matriz);
    double[][] nmatriz=matrizAdjunta(matriz);
    multiplicarMatriz(det,nmatriz);
    return nmatriz;
 

}
 
public void multiplicarMatriz(double n, double[][] matriz) {
    for(int i=0;i<matriz.length;i++)
        for(int j=0;j<matriz.length;j++)
            matriz[i][j]*=n;
    // imprimir multiplicacion escalar
    
    for (int i = 0; i < matriz.length; i++) {           
            for (int j = 0; j < matriz.length; j++) {
            System.out.printf(matriz[i][j]+"\t");  
            } 
            System.out.println("------------------");
    }
}
 
  public double[][] matrizAdjunta(double [][] matriz){
        return matrizTranspuesta(matrizCofactores(matriz));
    }
 
public double[][] matrizCofactores(double[][] matriz){
    double[][] nm=new double[matriz.length][matriz.length];
    for(int i=0;i<matriz.length;i++) {
        for(int j=0;j<matriz.length;j++) {
            double[][] det=new double[matriz.length-1][matriz.length-1];
            double detValor;
            for(int k=0;k<matriz.length;k++) {
                if(k!=i) {
                    for(int l=0;l<matriz.length;l++) {
                        if(l!=j) {
                        int indice1=k<i ? k : k-1 ;
                        int indice2=l<j ? l : l-1 ;
                        det[indice1][indice2]=matriz[k][l];
                        }
                    }
                }
            }
            detValor=determinante(det);
            nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
                                 // imprimir Resultado
        for (i = 0; i < matriz.length; i++) {           
            for (j = 0; j < matriz.length; j++) {
            System.out.printf(nm[i][j]+"\t");  
            } 
            System.out.println();   
        }

    }

    
    return nm;

}
 
public double[][] matrizTranspuesta(double[][] matriz){
    double[][]nuevam=new double[matriz[0].length][matriz.length];
    for(int i=0; i<matriz.length; i++)
    {
        for(int j=0; j<matriz.length; j++)
            nuevam[i][j]=matriz[j][i];
    }
    // imprimir Resultado
        for (int i = 0; i < matriz.length; i++) {           
            for (int j = 0; j < matriz.length; j++) {
            System.out.printf(nuevam[i][j]+"\t");  
            } 
            System.out.println();
    return nuevam;
        
}
 
    public double determinante(double[][] matriz){
    double det;
    if(matriz.length==2)
    {
        det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
        return det;
    }
    double suma=0;
    for(int i=0; i<matriz.length; i++){
    double[][] nm=new double[matriz.length-1][matriz.length-1];
        for(int j=0; j<matriz.length; j++){
            if(j!=i){
                for(int k=1; k<matriz.length; k++){
                int indice=-1;
                if(j<i)
                indice=j;
                else if(j>i)
                indice=j-1;
                nm[indice][k-1]=matriz[j][k];
                }
            }
        }
        if(i%2==0)
        suma+=matriz[i][0] * determinante(nm);
        else
        suma-=matriz[i][0] * determinante(nm);
    }
    return suma;
}

    public Operacion() {
    }
}
