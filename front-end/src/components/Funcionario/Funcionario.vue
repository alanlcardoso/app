<template>
  <div>
  <modal
    ref="modalRegistration"
    :title="'Funcionário'"
    :save="save"
    :minHeight="'60vh'"
    :minWidth="'60vw'">
    <div class="row col-sm-12">
      <q-input class="col-sm-2"
        v-model="entidade.matricula"
        :float-label="'Matrícula'"
        name="matricula"
        type="text"/>
    </div>
    <br/>
    <div class="row col-sm-12">
      <q-input class="col-sm-3"
        v-model="entidade.cpf"
        :float-label="'C.P.F'"
        name="cpf"
        type="text"/>
      <q-input class="col-sm-9"
        v-model="entidade.nome"
        :float-label="'Nome'"
        name="nome"
        type="text"/>
    </div>
    <br/>
    <q-select
      v-model="entidade.cargo.id"
      float-label="Cargo"
      :options="cargos"/>
    <br/>
    <q-select
      v-model="entidade.equipe.id"
      float-label="Equipe"
      :options="equipes"/>
    <br/>
    <div class="row col-sm-12">
      <q-input class="col-sm-6"
        v-model="entidade.maeNome"
        :float-label="'Mãe'"
        name="maeNome"
        type="text"/>
      <q-input class="col-sm-6"
        v-model="entidade.paiNome"
        :float-label="'Pai'"
        name="paiNome"
        type="text"/>
    </div>
    <br/>
    <div class="row col-sm-12">
      <q-input class="col-sm-6"
        v-model="entidade.email"
        :float-label="'E-mail'"
        name="email"
        type="text"/>
      <q-input class="col-sm-6"
        v-model="entidade.telefoneCelular"
        :float-label="'Celular'"
        name="telefoneCelular"
        type="text"/>
      </div>
    <br/>
    <div class="row col-sm-4">
      <q-datetime
        v-model="entidade.nascimentoData"
        :float-label="'Nascimento'"
        name="nascimentoData"
        class="col-sm-3" />
      <q-datetime
        v-model="entidade.admissaoData"
        :float-label="'Admissão'"
        name="admissaoData"
        class="col-sm-3" />
      <q-datetime
        v-model="entidade.desligamentoData"
        :float-label="'Desligamento'"
        name="desligamentoData"
        class="col-sm-3" />
    </div>
  </modal>
  <q-btn color="blue" @click.native="novoItem()" icon="library_add" label="Novo" />
  <br/>
  <br/>
  <b>Manutenção de Funcionário</b>
  <q-table
    :data="tableData"
    :columns="columns"
    selection="single"
    row-key="id"
    color="secondary"
    :no-data-label="'dataTable.noDataLabel'"
    :rowsPerPageLabel="'Linhas por páginas'"
  >
    <q-tr slot="body" slot-scope="props" :props="props">
      <q-tr :props="props">
        <q-btn size="sm" round dense color="secondary" flat icon="edit" @click.native="editItem(props.row)"  class="q-mr-xs" />
        <q-btn size="sm" round dense color="red" flat icon="delete" @click.native="deleteRow(props.row)" class="q-mr-xs" />
      </q-tr>
      <q-td key="matricula" :props="props">
        <span>{{ props.row.matricula }}</span>
      </q-td>
      <q-td key="nome" :props="props">
        <span>{{ props.row.nome }}</span>
      </q-td>
      <q-td key="cargo" :props="props">
        <span>{{ props.row.cargo.descricao }}</span>
      </q-td>
      <q-td key="equipe" :props="props">
        <span>{{ props.row.equipe.descricao }}</span>
      </q-td>
      <q-td key="celular" :props="props">
        <span>{{ props.row.telefoneCelular }}</span>
      </q-td>
    </q-tr>
  </q-table>
  </div>
</template>

<script>
import axios from 'axios'
import modal from '../modal.vue'

export default {
  components: {
    modal
  },
  data () {
    return {
      columns: [
        {
          name: 'matricula',
          required: true,
          label: 'Matricula',
          align: 'left',
          field: 'name',
          sortable: true
        },
        {
          name: 'nome',
          required: true,
          label: 'Nome',
          align: 'left',
          field: 'name',
          sortable: true
        },
        {
          name: 'cargo',
          required: true,
          label: 'Cargo',
          align: 'left',
          field: 'name',
          sortable: true
        },
        {
          name: 'equipe',
          required: true,
          label: 'Equipe',
          align: 'left',
          field: 'name',
          sortable: true
        },
        {
          name: 'celular',
          required: true,
          label: 'Celular',
          align: 'left',
          field: 'name'
        }
      ],
      tableData: [],
      dark: true,
      separator: 'horizontal',
      entidade: { cargo: {}, equipe: {} },
      cargos: [],
      equipes: []
    }
  },
  opened: false,
  computed: {
    tableClass () {
      if (this.dark) {
        return 'bg-black'
      }
    }
  },
  methods: {
    novoItem () {
      this.entidade = { cargo: {}, equipe: {} }
      this.$refs.modalRegistration.open()
    },
    editItem (row) {
      this.entidade = { cargo: {}, equipe: {} }
      this.entidade = Object.assign({}, row)
      this.$refs.modalRegistration.open()
    },
    save () {
      if (this.entidade.__index !== undefined) {
        delete this.entidade.__index
      }
      this.entidade.admissaoData = this.entidade.admissaoData.slice(0, 10)
      this.entidade.nascimentoData = this.entidade.nascimentoData.slice(0, 10)
      if (this.entidade.desligamentoData !== null && this.entidade.desligamentoData !== undefined) {
        this.entidade.desligamentoData = this.entidade.desligamentoData.slice(0, 10)
      }
      axios({
        method: 'post',
        url: 'http://localhost:8081/funcionario/',
        data: this.entidade
      })
        .then(response => {
          this.$q.notify({message: 'Salvo com Sucesso.', type: 'positive', icon: 'sentiment_satisfied_alt'})
          this.getFuncionarios()
          this.$refs.modalRegistration.close()
        })
        .catch(error => {
          this.$q.notify({message: error.response.data[0].mensagemUsuario, type: 'negative', icon: 'error'})
        })
    },
    init () {
      this.getFuncionarios()
      this.getCargos()
      this.getEquipes()
    },
    getEquipes () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/equipe/'
      })
        .then(response => {
          this.equipes = response.data.map(el => ({ value: el.id, label: el.descricao }))
        })
        .catch(() => {
          this.$q.notify({message: 'Não foi possível buscar os dados.', type: 'negative', icon: 'error'})
        })
    },
    getCargos () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/cargo/'
      })
        .then(response => {
          this.cargos = response.data.map(el => ({ value: el.id, label: el.descricao }))
        })
        .catch(() => {
          this.$q.notify({message: 'Não foi possível buscar os dados.', type: 'negative', icon: 'error'})
        })
    },
    getFuncionarios () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/funcionario/'
      })
        .then(response => {
          this.tableData = response.data
        })
        .catch(() => {
          this.$q.notify({message: 'Não foi possível buscar os dados.', type: 'negative', icon: 'error'})
        })
    },
    deleteRow (row) {
      this.$q.notify({
        color: 'red',
        message: `Deseja realmente remover o funcionario ${row.nome}?.`,
        actions: [{
          label: 'Sim',
          handler: () => {
            axios({
              method: 'delete',
              url: `http://localhost:8081/funcionario/ ${row.id}`
            })
              .then(response => {
                this.$q.notify({message: `Funcionario ${row.nome} excluído com sucesso.`, type: 'positive', icon: 'done'})
                this.getFuncionarios()
              })
              .catch(() => {
                this.$q.notify({message: 'Não foi possível deletar.', type: 'negative', icon: 'error'})
              })
          }
        }]
      })
    }
  },
  mounted () {
    this.init()
  }
}
</script>
