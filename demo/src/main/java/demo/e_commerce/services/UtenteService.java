package demo.e_commerce.services;

import demo.e_commerce.entities.Utente;
import demo.e_commerce.exceptions.MailAlreadyUsedException;
import demo.e_commerce.repositories.UtenteRepository;
import demo.e_commerce.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UtenteService 
{
    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente registerUser(Utente utente) throws MailAlreadyUsedException 
    {
        if (utenteRepository.existsByUsername(JwtUtil.getEmail()) ){ throw new MailAlreadyUsedException(); }

        utente.setUsername(JwtUtil.getEmail());
        return utenteRepository.save(utente);
    }

    @Transactional(readOnly = true)
    public List<Utente> getUtenti() { return utenteRepository.findAll(); }

    @Transactional(readOnly = true)
    public Boolean getUtente(String username) { return utenteRepository.existsByUsername(username); }
}