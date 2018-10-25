package net.explodingbush.ksoftapi.enums;

public enum Routes {
  REDDIT_URL("https://api.ksoft.si/meme/");
  
  private final String route;
  
  Routes(String route){
    this.route = route;
  }
  public String toString(){
    return this.route;
  }
}
