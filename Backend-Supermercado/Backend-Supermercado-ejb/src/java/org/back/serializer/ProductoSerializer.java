package org.back.serializer;

import java.io.IOException;
import org.back.hibernate.model.Producto;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *
 * @author Alejandro Garcia
 */
public class ProductoSerializer extends JsonSerializer<Producto>{

    @Override
    public void serialize(Producto producto, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, org.codehaus.jackson.JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("idproducto", producto.getIdproducto());
        jsonGenerator.writeStringField("nombre", producto.getNombreProducto());
        jsonGenerator.writeStringField("categoria", producto.getCategoriaIdCategoria().getNombreCategoria());
        jsonGenerator.writeEndObject(); 
    }
}