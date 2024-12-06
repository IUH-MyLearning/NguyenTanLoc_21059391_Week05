package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.dtos.CandidateDTO;
import fit.iuh.edu.vn.backend.models.Candidate;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;

    public List<CandidateDTO> findAllCandidates(){
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream().map(this::convertToDTO).toList();
    }

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                                   String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return (Page<Candidate>) candidateRepository.findAll(pageable);
    }

    private CandidateDTO convertToDTO(Candidate candidate){
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setId(candidate.getId());
        candidateDTO.setDob(candidate.getDob());
        candidateDTO.setEmail(candidate.getEmail());
        candidateDTO.setFullName(candidate.getFullName());
        candidateDTO.setPhone(candidate.getPhone());
        candidateDTO.setAddressId(candidate.getAddress().getId().toString());
        candidateDTO.setAccountId(candidate.getAccount().getId().toString());
        return candidateDTO;
    }
}
