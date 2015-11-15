package appcalculator.funciones;

public class OperacionesDeCalculadora {
    
    private String digito;
    private double resultado;
    private boolean suma;
    private boolean resta;
    private boolean multiplicacion;
    private boolean division;
    private boolean porcentaje;
    
    public OperacionesDeCalculadora(){
        
        digito = "";
        resultado = 0;
        suma = false;
        resta = false;
        multiplicacion = false;
        division = false;
        porcentaje = false;
        
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }
    
    public void agregarNum(int num){
        
        setDigito(digito+num);
        
    }
    
    public void suma(String num){
        
        this.resultado = Double.parseDouble(num);
        suma = true;
        setDigito("");
        
    }
    public void resta(String num){
        
        this.resultado = Double.parseDouble(num);
        resta = true;
        setDigito("");
        
    }
    public void multiplicacion(String num){
        
        this.resultado = Double.parseDouble(num);
        multiplicacion = true;
        setDigito("");
        
    }
    public void division(String num){
        
        this.resultado = Double.parseDouble(num);
        division = true;
        setDigito("");
        
    }
    public void porcentaje(String num){
        
        this.resultado = Double.parseDouble(num);
        porcentaje = true;
        setDigito("");
        
    }
    
    public double calculo(String numero){
        
        if(suma == true){
            
            resultado = resultado + Double.parseDouble(numero);
            
        }
        if(resta == true){
            
            resultado = resultado - Double.parseDouble(numero);
            
        }
        if(multiplicacion == true){
            
            resultado = resultado * Double.parseDouble(numero);
            
        }
        if(division == true){
            
            resultado = resultado / Double.parseDouble(numero);
            
        }
        if(porcentaje == true){
            
            resultado = resultado * ( Double.parseDouble(numero)/100);
            
        }
       
        suma = false;
        resta = false;
        multiplicacion = false;
        division = false;
        porcentaje = false;
        
        return resultado;
        
    }
    
    
}
