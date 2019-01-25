package mundo;

public class Proyectil extends Entidad implements Movimiento{
	
	public final static int RANGO_MAXIMO_JUGADOR=400;
	public final static int RANGO_MAXIMO_ROBOT=600;
	public final static int RANGO_MAXIMO_SPIDER=300;
	public final static int RANGO_MAXIMO_CRAB=200;
	
	public final static char JUGADOR='J';
	public final static char ROBOT_VOLADOR='V';
	public final static char SPIDER = 'S';
	public final static char MISIL = 'M';
	public final static char CRABBOT = 'C';

	private Proyectil siguiente;
	
	private boolean activo;
	
	private int rango;
	
	private int dano;
	
	private char tipo;

	private long tiempo;
	
	public Proyectil(char direccion,char view,int x,int y,char tipoP,int danoA) {
		setDireccion(direccion);
		tipo=tipoP;
		if(tipo==JUGADOR){
			rango=RANGO_MAXIMO_JUGADOR;
			setImagenActual("./data/Jugador/Shoot/Bullet.png");
		}
		else if(tipo==ROBOT_VOLADOR){
			rango=RANGO_MAXIMO_ROBOT;
			setImagenActual("./data/EnemigosGif/BulletRobotV.png");
		}else if(tipo==SPIDER){
			rango =RANGO_MAXIMO_SPIDER;
			setImagenActual("./data/Spider/proyectilSpider.png");
		}
		else if(tipo==CRABBOT){
			rango =RANGO_MAXIMO_CRAB;
			setImagenActual("./data/Spider/proyectilSpider.png");
		}
		if(view==Jugador.AIM_UP){
			if(getDireccion()==Entidad.DERECHA){
				setPosXIni(x+10);
				setPosYIni(y-40);
				setDireccion(Jugador.AIM_UP);
			}
			else{
				setPosXIni(x+30);
				setPosYIni(y-40);
				setDireccion(Jugador.AIM_UP);
			}
		}
		else if(view==Jugador.AIM_DOWN){
			if(getDireccion()==Entidad.DERECHA){
                setPosXIni(x+80);
                setPosYIni(y+43);

            }
            else if(getDireccion()==Entidad.IZQUIERDA){
                setPosXIni(x-30);
                setPosYIni(y+43);

            }
            else{
                setPosXIni(x+40);
                setPosYIni(y+40);
                setDireccion(Jugador.AIM_DOWN);
            }
		
		}
		else if(view==CrabBot.AIM_DIAGONAL){
			if(getDireccion()==Entidad.DERECHA){
                setPosXIni(x+140);
                setPosYIni(y+70);
            }
            else if(getDireccion()==Entidad.IZQUIERDA){
                setPosXIni(x+140);
                setPosYIni(y+70);
            }
		}
		else if(getDireccion()==Entidad.DERECHA){
			setPosXIni(x+80);
			setPosYIni(y+25);
		}
		else if(getDireccion()==Entidad.IZQUIERDA){
			setPosXIni(x-40);
			setPosYIni(y+25);
		}
		setDano(danoA);
		setPosX(getPosXIni());
		setPosY(getPosYIni());
		siguiente=null;
		activo=true;
		setTiempo(System.currentTimeMillis());
	}
	
	public void movimiento(){
		if(getDireccion()==Entidad.DERECHA){
			if(tipo==CRABBOT){
				if(getPosX()>=getPosXIni()-rango){
					setPosX(getPosX()-5);
					setPosY(getPosY()+3);
				}
				
				else{
					activo=false;
				}
			}
			else{
				if(getPosX()<=getPosXIni()+rango){
					setPosX(getPosX()+5);
				}
				
				else{
					activo=false;
				}
			}
		}
		else if(getDireccion()==Entidad.IZQUIERDA){
				if(tipo==CRABBOT){
					if(getPosX()<=getPosXIni()+rango){
						setPosX(getPosX()+5);
						setPosY(getPosY()+3);
					}
					
					else{
						activo=false;
					}
				}
				else{
					if(getPosX()>=getPosXIni()-rango){
						setPosX(getPosX()-5);
					}
					
					else{
						activo=false;
					}
				}
		
			}
		else if(getDireccion()==Jugador.AIM_UP){
			if(getPosY()>=getPosYIni()-rango){
				setPosY(getPosY()-5);	
			}
			else{
				activo=false;
			}
		}
		else if(getDireccion()==Jugador.AIM_DOWN){
			if(getPosY()<=getPosYIni()+rango){
				setPosY(getPosY()+5);
			}
			else{
				
				activo=false;
			}
		}
	}

	public Proyectil getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Proyectil siguiente) {
		this.siguiente = siguiente;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public long getTiempo() {
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}

	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}


	
	
	
}
