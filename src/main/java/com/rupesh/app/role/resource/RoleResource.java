package com.rupesh.app.role.resource;

import com.rupesh.app.role.model.Role;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/roles")
@RequiredArgsConstructor
public class RoleResource {

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Role>>> getRoles() {
        return ResponseEntity.ok(GlobalResponse.success("All roles retrieved successfully",List.of(new Role())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<Role>> getById(@PathVariable("id") long id) {

        return ResponseEntity.ok(GlobalResponse.success("Role found", new Role()));
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Role>> createRole(@RequestBody Role role) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GlobalResponse.success("Role created", role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<Role>> updateRole(@PathVariable("id") long id, @RequestBody Role updatedRole) {

        return ResponseEntity.ok(GlobalResponse.success("Role updated", new Role()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> deleteRole(@PathVariable("id") long id) {

        return ResponseEntity.ok(GlobalResponse.success("Role deleted"));

    }

}
