package hu.uni.miskolc.teszteles.levelezo.dao.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import hu.uni.miskolc.teszteles.levelezo.dao.AutoDAO;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.AutoNemTalalhato;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Collection;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class AutoDAOMongo implements AutoDAO {
    private MongoCollection<AutoPojo> collection;

    public AutoDAOMongo(String uri, String database, String collection) {
        ConnectionString connectionString = new ConnectionString(uri);
        CodecRegistry pojoCodecRegistry =
                fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry =
                fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().
                applyConnectionString(connectionString).
                codecRegistry(codecRegistry).build();
        MongoClient client = MongoClients.create(clientSettings);
        this.collection = client.getDatabase(database).
                getCollection(collection, AutoPojo.class);
    }

    @Override
    public Collection<Auto> readAutos() {
        return collection.find().map(AutoPojoConverter::convertPojoToAuto).
                into(new ArrayList());
    }

    @Override
    public Auto readAutoById(String rendszam) throws AutoNemTalalhato {
        Auto auto = collection.find(com.mongodb.client.model.Filters.eq(
                "_id", rendszam)).map
                (AutoPojoConverter::convertPojoToAuto).first();
        if (auto == null){
            throw new AutoNemTalalhato();
        }
        return auto;
    }



    @Override
    public void createAuto(Auto auto) {
            collection.insertOne(AutoPojoConverter.convertAutoToPojo(auto));
    }

    @Override
    public void updateAuto(Auto auto) throws AutoNemTalalhato {
        readAutoById(auto.getRendszam());
        AutoPojo pojo = AutoPojoConverter.convertAutoToPojo(auto);
        collection.findOneAndReplace(com.mongodb.client.model.Filters.eq(
                "_id", pojo.rendszam),pojo);
    }

    public void clearCollection(){
        collection.drop();
    }
}
