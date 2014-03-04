package com.github.springular.server.component.employee.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmployeeBE is a Querydsl query type for EmployeeBE
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmployeeBE extends EntityPathBase<EmployeeBE> {

    private static final long serialVersionUID = -1165091459L;

    public static final QEmployeeBE employeeBE = new QEmployeeBE("employeeBE");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath login = createString("login");

    public final StringPath nationality = createString("nationality");

    public final ListPath<SalaryBE, QSalaryBE> salaries = this.<SalaryBE, QSalaryBE>createList("salaries", SalaryBE.class, QSalaryBE.class, PathInits.DIRECT2);

    public QEmployeeBE(String variable) {
        super(EmployeeBE.class, forVariable(variable));
    }

    public QEmployeeBE(Path<? extends EmployeeBE> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployeeBE(PathMetadata<?> metadata) {
        super(EmployeeBE.class, metadata);
    }

}

