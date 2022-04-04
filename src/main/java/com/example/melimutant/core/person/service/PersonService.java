package com.example.melimutant.core.person.service;

import com.example.melimutant.port.IPersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {
    private static final String FIRST_SEQUENCE = "AAAA";
    private static final String SECOND_SEQUENCE = "CCCC";
    private static final String THIRD_SEQUENCE = "GGGG";
    private static final int MINIMUM_MUTANT_SEQUENCES = 3;

    @Override
    public boolean isMutant(String[] dna) throws Exception {
        return isMutantSequence(dna) >= MINIMUM_MUTANT_SEQUENCES;
    }

    private int isMutantSequence(String[] dnaSequences) {
        return getHorizontalSequences(dnaSequences) +
                getVerticalSequences(dnaSequences) +
                getLeftDiagonalSequences(dnaSequences) +
                getRightDiagonalSequences(dnaSequences);
    }

    private int getHorizontalSequences(String[] dnaSequences) {
        int mutantSequences = 0;
        for (String dna : dnaSequences) {
            mutantSequences += isMutantSequence(dna) ? 1 : 0;
        }
        return mutantSequences;
    }

    private int getVerticalSequences(String[] dnaSequences) {
        int mutantSequences = 0;
        for (int filesCounter = 0; filesCounter < dnaSequences.length; filesCounter++) {
            StringBuilder vertical = new StringBuilder();
            for (int columnCounter = 0; columnCounter < dnaSequences[filesCounter].length(); columnCounter++) {
                vertical.append(dnaSequences[columnCounter].charAt(filesCounter));
            }
            mutantSequences += isMutantSequence(vertical.toString()) ? 1 : 0;
        }
        return mutantSequences;
    }

    private int getLeftDiagonalSequences(String[] dnaSequences) {
        int mutantSequences = 0;
        int i;
        int j;
        for (int k = 0; k <= dnaSequences[0].length() - 1; k++) {
            StringBuilder dnaToValidate = new StringBuilder();
            i = k;
            j = 0;
            while (i >= 0) {
                dnaToValidate.append(dnaSequences[i].charAt(j));
                i = i - 1;
                j = j + 1;
            }
            mutantSequences += isMutantSequence(dnaToValidate.toString()) ? 1 : 0;
        }
        for (int k = 0; k < dnaSequences.length; k++) {
            StringBuilder dnaToValidate = new StringBuilder();
            i = dnaSequences[0].length() - 1;
            j = k;
            while (j <= dnaSequences.length - 1) {
                dnaToValidate.append(dnaSequences[i].charAt(j));
                i = i - 1;
                j = j + 1;
            }
            mutantSequences += isMutantSequence(dnaToValidate.toString()) ? 1 : 0;
        }
        return mutantSequences;
    }

    private int getRightDiagonalSequences(String[] dnaSequences) {
        int mutantSequences = 0;
        int i;
        int j;
        for (int k = 0; k < dnaSequences.length; k++) {
            StringBuilder dnaToValidate = new StringBuilder();
            i = 0;
            j = k + 1;
            while (j <= dnaSequences.length - 1) {
                dnaToValidate.append(dnaSequences[i].charAt(j));
                i = i + 1;
                j = j + 1;
            }
            mutantSequences += isMutantSequence(dnaToValidate.toString()) ? 1 : 0;
        }
        for (int k = 0; k <= dnaSequences[0].length() - 1; k++) {
            StringBuilder dnaToValidate = new StringBuilder();
            i = k;
            j = 0;
            while (i <= dnaSequences[0].length() - 1) {
                dnaToValidate.append(dnaSequences[i].charAt(j));
                i = i + 1;
                j = j + 1;
            }
            mutantSequences += isMutantSequence(dnaToValidate.toString()) ? 1 : 0;
        }
        return mutantSequences;
    }

    private boolean isMutantSequence(String dna) {
        return dna.contains(FIRST_SEQUENCE) || dna.contains(SECOND_SEQUENCE) || dna.contains(THIRD_SEQUENCE);
    }


}
