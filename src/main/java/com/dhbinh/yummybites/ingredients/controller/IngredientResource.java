package com.dhbinh.yummybites.ingredients.controller;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.ingredients.service.IngredientService;
import com.dhbinh.yummybites.ingredients.service.dto.IngredientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/ingredients")
@PreAuthorize("hasAnyRole('OWNER','COOK')")
public class IngredientResource {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<IngredientDTO> create(@Valid @RequestBody IngredientDTO ingredientDTO) {
        IngredientDTO dto = ingredientService.create(ingredientDTO);
        return ResponseEntity.created(URI.create("/api/ingredients" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> findAll() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @PutMapping("/{ingredient-id}/add")
    public ResponseEntity<IngredientDTO> add(@PathVariable("ingredient-id") Long ID,
                                             @RequestParam("quantity")
                                             @Min(value = 1, message = ErrorMessage.INGREDIENT_QUANTITY_LESS_THAN_ONE)
                                             Double quantity) {
        return ResponseEntity.ok(ingredientService.add(ID, quantity));
    }

    @PutMapping("/{ingredient-id}/deducted")
    public ResponseEntity<IngredientDTO> deducted(@PathVariable("ingredient-id") Long ID,
                                                  @RequestParam("quantity") @Min(value = 1, message = ErrorMessage.INGREDIENT_QUANTITY_LESS_THAN_ONE)
                                                  Double quantity) {
        return ResponseEntity.ok(ingredientService.deducted(ID, quantity));
    }

    @GetMapping("/with-specification")
    public ResponseEntity<List<IngredientDTO>> findByNameLikeOrQuantityLessThanOrEqualTo(@RequestParam("name") String name,
                                                                                         @RequestParam("quantity") String quantityString) {
        return ResponseEntity.ok(ingredientService.findByNameLikeOrQuantityLessThanOrEqualTo(name, quantityString));
    }
}
