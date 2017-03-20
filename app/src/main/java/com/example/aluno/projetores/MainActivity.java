package com.example.aluno.projetores;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.example.aluno.projetores.fragments.CadastrarProfessoresFragment;
import com.example.aluno.projetores.fragments.CadastrarProjetoresFragment;
import com.example.aluno.projetores.fragments.EmprestimosFragment;
import com.example.aluno.projetores.fragments.HomeFragment;
import com.example.aluno.projetores.fragments.ProfessoresFragment;
import com.example.aluno.projetores.fragments.ProjetoresFragment;
import com.example.aluno.projetores.models.Emprestimo;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.models.Projetor;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateViews();

        setToolbar();

        setNavigationDrawer(savedInstanceState);

//        insertFakeData(getApplicationContext());
    }

    private void instantiateViews(){
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void setNavigationDrawer(Bundle savedInstanceState) {
        //header of menu
        headerNavigationLeft = new AccountHeaderBuilder().withActivity(this).addProfiles(
                new ProfileDrawerItem().withEmail("Secretária").withIcon(R.drawable.ic_account_circle_white),
                new ProfileDrawerItem().withEmail("Professor").withIcon(R.drawable.ic_account_circle_white),
                new ProfileDrawerItem().withEmail("Técnico").withIcon(R.drawable.ic_account_circle_white)
        ).withHeaderBackground(R.color.colorPrimaryDark).build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(0).withName("Emprestar/Devolver projetor").withIcon(R.drawable.ic_crop_free);
        SectionDrawerItem sectionProjetores = new SectionDrawerItem().withIdentifier(1).withName("PROJETORES");
        PrimaryDrawerItem projetores = new PrimaryDrawerItem().withIdentifier(2).withName("Projetores").withIcon(R.drawable.ic_videocam);
        PrimaryDrawerItem adicionarProjetor = new PrimaryDrawerItem().withIdentifier(3).withName("Adicionar projetor").withIcon(R.drawable.ic_video_call);
        SectionDrawerItem sectionProfessores = new SectionDrawerItem().withIdentifier(4).withName("PROFESSORES");
        PrimaryDrawerItem professores = new PrimaryDrawerItem().withIdentifier(5).withName("Professores").withIcon(R.drawable.ic_person);
        PrimaryDrawerItem adicionarProfessor = new PrimaryDrawerItem().withIdentifier(6).withName("Adicionar professor").withIcon(R.drawable.ic_person_add);
        SectionDrawerItem sectionEmprestimos = new SectionDrawerItem().withIdentifier(7).withName("EMPRÉSTIMOS");
        PrimaryDrawerItem emprestimos = new PrimaryDrawerItem().withIdentifier(8).withName("Emprestimos").withIcon(R.drawable.ic_assignment);


        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true).withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        home,
                        sectionProjetores,
                        projetores,
                        adicionarProjetor,
                        sectionProfessores,
                        professores,
                        adicionarProfessor,
                        sectionEmprestimos,
                        emprestimos)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //TODO: Melhorar o jeito desse switch - Magic Numbers
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        switch(position) {
                            case 1:
                                ft.replace(R.id.flMainContent, new HomeFragment());
                                mToolbar.setTitle("Leitor de QRCode");
                                ft.commit();
                                break;

                            case 3:
                                ft.replace(R.id.flMainContent, new ProjetoresFragment());
                                mToolbar.setTitle("Projetores");
                                ft.commit();
                                break;

                            case 4:
                                ft.replace(R.id.flMainContent, new CadastrarProjetoresFragment());
                                mToolbar.setTitle("Projetores");
                                ft.commit();
                                break;

                            case 6:
                                ft.replace(R.id.flMainContent, new ProfessoresFragment());
                                mToolbar.setTitle("Professores");
                                ft.commit();
                                break;

                            case 7:
                                ft.replace(R.id.flMainContent, new CadastrarProfessoresFragment());
                                mToolbar.setTitle("Professores");
                                ft.commit();
                                break;

                            case 9:
                                ft.replace(R.id.flMainContent, new EmprestimosFragment());
                                mToolbar.setTitle("Emprestimos");
                                ft.commit();
                                break;
                        }
                        return false;
                    }
                }).withAccountHeader(headerNavigationLeft)
                .build();

        navigationDrawerLeft.setSelection(home);
    }

    public void insertFakeData(Context applicationContext) {

        ProfessoresFragment professoresFragment = new ProfessoresFragment();
        ProjetoresFragment projetoresFragment = new ProjetoresFragment();
        EmprestimosFragment emprestimosFragment = new EmprestimosFragment();

        ArrayList<Professor> professores = professoresFragment.buscarProfessores(applicationContext);
        ArrayList<Projetor> projetores = projetoresFragment.buscarProjetores(applicationContext);
        ArrayList<Emprestimo> emprestimos = emprestimosFragment.buscarEmprestimos(applicationContext);

        if(professores == null || professores.size() == 0) {

            professores = new ArrayList<>();

            professores.add(new Professor("Rafael Durelli", "0000000000", "DCC", 0));
            professores.add(new Professor("Ricardo Terra", "1111111111", "DCC", 1));
            professores.add(new Professor("Luiz Henrique", "2222222222", "DCC", 2));
            professores.add(new Professor("Antonio Maria", "3333333333", "DCC", 3));
            professores.add(new Professor("Marluce Pereira", "4444444444", "DCC", 4));

            professoresFragment.save(professores, applicationContext);
        }

        if(projetores == null || projetores.size() == 0) {

            projetores = new ArrayList<>();

            projetores.add(new Projetor("Phillips", "BBR1001", 0, "0101010101", 0));
            projetores.add(new Projetor("Phillips", "KXT-8785", 0, "0202020202", 1));
            projetores.add(new Projetor("Epson", "Mod2342", 0, "0303030303", 2));
            projetores.add(new Projetor("Epson", "Mod9783", 0, "0404040404", 3));
            projetores.add(new Projetor("Multilaser", "Ruim", 2, "0505050505", 4));
            projetores.add(new Projetor("DaChina", "SM", 2, "0606060606", 5));
            projetores.add(new Projetor("DaChina", "SM", 2, "0707070707", 6));
            projetores.add(new Projetor("DaChina", "SM", 2, "0808080808", 7));
            projetores.add(new Projetor("DaChina", "SM", 2, "0909090909", 8));

            projetoresFragment.save(projetores, applicationContext);
        }

        if(emprestimos == null || emprestimos.size() == 0) {

            emprestimos = new ArrayList<>();

            emprestimos.add(new Emprestimo(0, 0, new Date(), null, 0));
            emprestimos.add(new Emprestimo(1, 1, new Date(), null, 1));
            emprestimos.add(new Emprestimo(2, 2, new Date(), null, 2));
            emprestimos.add(new Emprestimo(3, 0, new Date(), null, 3));
            emprestimos.add(new Emprestimo(4, 1, new Date(), null, 4));
            emprestimos.add(new Emprestimo(5, 2, new Date(), new Date(), 5));
            emprestimos.add(new Emprestimo(6, 3, new Date(), new Date(), 6));

            emprestimosFragment.save(emprestimos, applicationContext);
        }

    }

}
