package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Author;
import com.library.libraryMgtSystem.data.repositories.AuthorRepository;
import com.library.libraryMgtSystem.dtos.request.CreateAuthorRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAdminResponse;
import com.library.libraryMgtSystem.dtos.response.CreateAuthorResponse;
import com.library.libraryMgtSystem.exceptions.AuthorException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public CreateAuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest) {
        if (authorRepository.findAuthorByEmail(createAuthorRequest.getEmail().toLowerCase()).isPresent())
            throw new AuthorException("Author already exists");
        Author author = new Author();
        author.setFirstName(createAuthorRequest.getFirstName());
        author.setLastName(createAuthorRequest.getLastName());
        author.setEmail(createAuthorRequest.getEmail());
        authorRepository.save(author);

        CreateAuthorResponse createAuthorResponse = new CreateAuthorResponse();
        createAuthorResponse.setMessage("Successful");
        createAuthorResponse.setStatusCode(201);
        return createAuthorResponse;
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(()-> new AuthorException("Author with id "+ id +" does not exist"));
    }

    @Override
    public Author getAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email).orElseThrow(()-> new AuthorException("Author does not exist"));
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();

    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }


    @Override
    public String deleteAuthorByEmail(String email) {
        getAuthorByEmail(email);
        authorRepository.deleteAuthorByEmail(email);
        return "Author deleted successfully";
    }
}
