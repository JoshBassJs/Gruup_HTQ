package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.PersonaDao;
import com.example.proyectocalid.modelo.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {

    
    @Autowired
    private PersonaDao data;

    @Override
    public List<Persona> Listar() {
        return (List<Persona>) data.findAll();
    }

    @Override
    public Optional<Persona> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Persona p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Persona Registrar(Persona p) {
        data.save(p);
        // Listado de Personas
        List<Persona> personas = Listar();
        // Recuperar última persona registrada
        Persona persona = personas.get(personas.size() - 1);
        int idPersona = persona.getIdPersona();
        // Buscar por Id
        Optional<Persona> datoRecuperado = ListarId(idPersona);
        return datoRecuperado.get();
    }
}
