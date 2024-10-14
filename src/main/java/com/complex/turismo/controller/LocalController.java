package com.complex.turismo.controller;

import com.complex.turismo.model.Local;
import com.complex.turismo.model.StatusLocal;
import com.complex.turismo.repository.LocalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("local")
public class LocalController {

    @Autowired
    LocalRepository localRepository;

    @GetMapping("cadastro")
    public String cadastrar(Local local, Model model) {
        model.addAttribute("status", StatusLocal.values());
        return "local/cadastro";
    }

    @PostMapping("salvar")
    public String salvar(@Valid Local local, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("status", StatusLocal.values());
            return "local/cadastro";
        }
        localRepository.save(local);
        return "redirect:/local/listar";
    }

    @GetMapping("listar")
    public String listagem(Model model) {
        model.addAttribute("local", localRepository.findAll());
        return "local/listar";
    }

    @GetMapping("pesquisa")
    public String exibirPesquisa() {
        return "local/pesquisa";
    }

    @GetMapping("pesquisar")
    public String pesquisar(@RequestParam(value = "nome", required = false) String nome, Model model) {
        List<Local> locais;
        if (nome != null && !nome.isEmpty()) {
            locais = localRepository.findByNameContainingIgnoreCase(nome);
        } else {
            locais = localRepository.findAll();
        }
        model.addAttribute("local", locais);
        return "local/pesquisar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Local local = localRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Local inválido: " + id));
        model.addAttribute("local", local);
        model.addAttribute("status", StatusLocal.values());
        return "local/cadastro";
    }

    @PostMapping("editar/{id}")
    public String atualizar(@PathVariable Long id, @Valid Local local, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("status", StatusLocal.values());
            return "local/cadastro";
        }
        local.setId(id);
        localRepository.save(local);
        return "redirect:/local/listar";
    }

    @GetMapping("excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Local local = localRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Local inválido: " + id));
        model.addAttribute("local", local);
        return "local/confirmarExclusao";
    }

    @PostMapping("excluir/{id}")
    public String excluir(@PathVariable Long id) {
        Local local = localRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Local inválido: " + id));
        localRepository.delete(local);
        return "redirect:/local/listar";
    }
}