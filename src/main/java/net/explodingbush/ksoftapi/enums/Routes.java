package net.explodingbush.ksoftapi.enums;

public enum Routes {
  REDDIT("https://api.ksoft.si/meme/");
  
  private final String route;
  
  private Routes(String route){
    this.route = route;
  }
  public String toString(){
    return this.route;
  }
}
