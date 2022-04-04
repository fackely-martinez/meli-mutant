package com.example.melimutant.adapter.api.mutant;

import com.example.melimutant.adapter.api.mutant.contract.Request;
import com.example.melimutant.adapter.api.mutant.mapper.IMutantMapper;
import com.example.melimutant.core.person.exception.NoMutantException;
import com.example.melimutant.core.person.exception.PersonExistsException;
import com.example.melimutant.port.IPersonUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/mutant")
public class MutantAPI {
    private static final Logger logger = LoggerFactory.getLogger(MutantAPI.class);

    private final IMutantMapper mutantMapper;
    private final IPersonUseCase personUseCase;

    public MutantAPI(IMutantMapper mapper,
                     IPersonUseCase personUseCase) {
        this.mutantMapper = mapper;
        this.personUseCase = personUseCase;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid Request request) {
        try {
            if (!request.isValid()) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            personUseCase.execute(
                    mutantMapper.requestToCommand(request));
            return ResponseEntity.ok(null);
        } catch (PersonExistsException personExistsException) {
            logger.error(personExistsException.toString());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (NoMutantException noMutantException) {
            logger.error(noMutantException.toString());
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (Exception exception) {
            logger.error(exception.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
