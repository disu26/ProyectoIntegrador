export interface Risk {
    id?: string;
    projectId?: string;
    name: string;
    userId: string;
    detectedDate?: Date;
    endedDate?: Date;
    labels: string[];
    description: string;
    // (Abierto; mitigado; Cerrado; Problema)
    riskState: string;
    // (Interna; Externa)
    audience: string;
    // (Costo; Tiempo; Calidad)
    category: string;
    // (Riesgo de producto o calidad; Riesgo de proyecto)
    riskType: string;
    detailsRiskType: string;
    probability: number;
    impactValue: number;
    mitigationPlan: string;
    responsibleMitigationMails: string[];
    contingencyPlan: string;
    responsibleContingencyMails: string[];
    state?: number;
  }