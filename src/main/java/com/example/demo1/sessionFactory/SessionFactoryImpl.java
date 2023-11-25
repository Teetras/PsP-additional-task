package com.example.demo1.sessionFactory;

import com.example.demo1.entity.Gem;
import com.example.demo1.entity.Necklace;
import com.example.demo1.entity.User;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor
public class SessionFactoryImpl {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Создаем объект Configuration и загружаем настройки из файла hibernate.cfg.xml
                Configuration configuration = new Configuration().configure();

                // Создаем объект MetadataSources и добавляем аннотированные классы
                MetadataSources metadataSources = new MetadataSources(
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build());
                metadataSources.addAnnotatedClass(Gem.class);
                metadataSources.addAnnotatedClass(Necklace.class);
                metadataSources.addAnnotatedClass(User.class);

                // Создаем объект Metadata и строим SessionFactory
                Metadata metadata = metadataSources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}