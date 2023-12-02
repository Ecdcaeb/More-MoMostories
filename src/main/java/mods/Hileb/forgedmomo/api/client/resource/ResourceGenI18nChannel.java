package mods.Hileb.forgedmomo.api.client.resource;

import java.io.*;
import java.util.HashSet;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/30 12:09
 **/
public class ResourceGenI18nChannel {
    protected HashSet<Language> languages=new HashSet<>();
    public void commit(String line){
        for(Language language:languages){
            language.commit(line);
        }
    }
    public void pop(String key){
        for (Language language:languages){
            language.pop(key);
        }
    }
    public Language of(String name){
        Language language=new Language(name);
        languages.add(language);
        return language;
    }
    public void save(File root) throws IOException{
        for(Language language:languages){
            language.returnToFile(root).mkdirs();
        }
        languages=null;
    }
    public final static class Language {
        public final String name;
        public StringBuilder text=new StringBuilder();
        Language(String nameIn){
            name=nameIn;
        }
        public void commit(String line){
            text.append('\n').append(line);
        }
        public void put(String key,String value){
            text.append('\n').append(key).append('=').append(value);
        }
        void pop(String key){
            text.append('\n').append(key).append('=');
        }
        public void push(String key){
            text.append(key);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Language && ((Language)obj).name.equals(name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return "Language "+name;
        }
        File returnToFile(File root) throws IOException {
            File file=new File(root,name+".lang");
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(text.toString());
            text=null;
            fileWriter.close();
            return file;
        }
    }
}
